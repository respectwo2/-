package com.example.jinyengandothers.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.analysis.CashFlow;
import org.ta4j.core.backtest.BacktestExecutor;
import org.ta4j.core.backtest.BarSeriesManager;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.StochasticOscillatorKIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandFacade;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.HighPriceIndicator;
import org.ta4j.core.indicators.helpers.LowPriceIndicator;
import org.ta4j.core.indicators.supertrend.SuperTrendIndicator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.reports.TradingStatement;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;
import org.ta4j.core.rules.InPipeRule;
import org.ta4j.core.rules.OpenedPositionMinimumBarCountRule;
import org.ta4j.core.rules.OverIndicatorRule;
import org.ta4j.core.rules.UnderIndicatorRule;

import com.example.jinyengandothers.dao.CoinPriceDao;
import com.example.jinyengandothers.dto.CoinCombiCashflowDto;
import com.example.jinyengandothers.dto.CoinPriceDto;
import com.example.jinyengandothers.dto.CoinRatioDto;


@Service
public class BackTestingServiceImpl{
	
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Logger LOG = LoggerFactory.getLogger(BackTestingServiceImpl.class);
    private List<BarSeries> coinBarSeriesList;
	
	@Autowired
	private CoinPriceDao coinPriceDao;	

	public BackTestingServiceImpl() {
		super();
		this.coinBarSeriesList = new ArrayList<>();
	}

	private void loadCoinDataTimeSeries(List<CoinRatioDto> coinRatios) {
		List<String> coinNames = coinRatios.stream().map(e -> e.getCoinName()).collect(Collectors.toCollection(ArrayList::new));
		
		List<List<CoinPriceDto>> coinPricesList = coinPriceDao.selectSameLengthCoinPrices(coinNames);
		
		for(List<CoinPriceDto> coinPrices : coinPricesList) {
			BarSeries coinHistoricalPrices = new BaseBarSeries(coinNames.get(coinPricesList.indexOf(coinPrices)));
			for(CoinPriceDto coinPrice: coinPrices) {
				LocalDateTime parsedEndTime = LocalDateTime.parse(coinPrice.getEndTime(),DATE_FORMAT);
				ZonedDateTime zonedDateEndTime = ZonedDateTime.of(parsedEndTime, ZoneId.systemDefault());
				coinHistoricalPrices.addBar(zonedDateEndTime,coinPrice.getOpenPrice(),coinPrice.getHighPrice(),coinPrice.getLowPrice(),coinPrice.getClosePrice(), coinPrice.getVolume());
			}
			this.coinBarSeriesList.add(coinHistoricalPrices);
		}		
	}

	private List<Strategy> createStrategies(List<String> strategyNames, BarSeries barSeries) {
				
		List<Strategy> strategies = new ArrayList<>();
		
		for(String strategyName : strategyNames) {
			strategies.add(createStrategy(strategyName, barSeries));
		}
				
		return strategies;
	} 

	private Strategy createStrategy(String strategyName, BarSeries barSeries) {
		
		BollingerBandFacade bollinger = new BollingerBandFacade(barSeries,20,2);
		ClosePriceIndicator closePrice = new ClosePriceIndicator(barSeries);
		Rule entryRule = null;
		Rule exitRule = null;
		
		switch(strategyName) {
		case "SMA":
			SMAIndicator smaShort = new SMAIndicator(closePrice, 7);
	        SMAIndicator smaLong = new SMAIndicator(closePrice, 25);
	        entryRule = new CrossedUpIndicatorRule(smaShort, smaLong);
	        exitRule = new CrossedDownIndicatorRule(smaShort, smaLong);
			break;
		case "EMA":
			EMAIndicator emaShort = new EMAIndicator(closePrice, 7);
			EMAIndicator emaLong = new EMAIndicator(closePrice, 25);
			entryRule = new CrossedUpIndicatorRule(emaShort, emaLong);
			exitRule = new CrossedDownIndicatorRule(emaShort, emaLong);
			break;
		case "Bollinger1":
//			매수신호: 종가 < 볼린저밴드 하한선
			entryRule = new CrossedUpIndicatorRule(bollinger.lower(),closePrice);
//			매도신호: 종가 > 볼린저밴드 상한선 
			exitRule = new CrossedDownIndicatorRule(bollinger.upper(),closePrice);
			break;
		case "Bollinger2":
//			매수신호: 종가 > 볼린저밴드 상한선
			entryRule = new CrossedUpIndicatorRule(closePrice, bollinger.upper());
//			매도신호: 종가 < 볼린저밴드 상한선
			exitRule = new CrossedDownIndicatorRule(closePrice, bollinger.upper());
			break;
		case "VBI":
			entryRule = new InPipeRule(new VolatilityIndicator(barSeries), new HighPriceIndicator(barSeries), new LowPriceIndicator(barSeries));
			exitRule = new OpenedPositionMinimumBarCountRule(1);
			break;
		case "RSI":				
	        SMAIndicator shortSma = new SMAIndicator(closePrice, 5);
	        SMAIndicator longSma = new SMAIndicator(closePrice, 200);
	        RSIIndicator rsi = new RSIIndicator(closePrice, 2);
	        entryRule = new OverIndicatorRule(shortSma, longSma).and(new CrossedDownIndicatorRule(rsi, 5)).and(new OverIndicatorRule(shortSma, closePrice));
	        exitRule = new UnderIndicatorRule(shortSma, longSma).and(new CrossedUpIndicatorRule(rsi, 95)).and(new UnderIndicatorRule(shortSma, closePrice));
			break;
		case "Momentum":
	        EMAIndicator shortEma = new EMAIndicator(closePrice, 9);
	        EMAIndicator longEma = new EMAIndicator(closePrice, 26);
	        StochasticOscillatorKIndicator stochasticOscillK = new StochasticOscillatorKIndicator(barSeries, 14); 
	        MACDIndicator macd = new MACDIndicator(closePrice, 9, 26);
	        EMAIndicator emaMacd = new EMAIndicator(macd, 18);
	        entryRule = new OverIndicatorRule(shortEma, longEma).and(new CrossedDownIndicatorRule(stochasticOscillK, 20)).and(new OverIndicatorRule(macd, emaMacd));
	        exitRule = new UnderIndicatorRule(shortEma, longEma).and(new CrossedUpIndicatorRule(stochasticOscillK, 80)).and(new UnderIndicatorRule(macd, emaMacd));
			break;
		case "Supertrend":
			SuperTrendIndicator superTrend = new SuperTrendIndicator(barSeries);
			entryRule = new CrossedUpIndicatorRule(closePrice, superTrend.getSuperTrendUpperBandIndicator());
			exitRule = new CrossedDownIndicatorRule(closePrice, superTrend.getSuperTrendUpperBandIndicator());
			break;
		default:
			break;
		}
		
		
		return new BaseStrategy(strategyName,entryRule, exitRule);
	}
	
	public List<CoinCombiCashflowDto> getCoinCombinationResult(List<CoinRatioDto> coinRatios, List<String> strategyNames, double initialEquity) {
		List<String> coinNames = coinRatios.stream().map(e -> e.getCoinName()).collect(Collectors.toCollection(ArrayList::new));
		List<CoinCombiCashflowDto> CoinCombiCashflows = new ArrayList<>();
		List<Double> ratios = new ArrayList<>();
		
		if(this.coinBarSeriesList.isEmpty()) {
			loadCoinDataTimeSeries(coinRatios);
		}
		
		for (BarSeries barSeries : this.coinBarSeriesList) {
			List<Strategy> strategies = createStrategies(strategyNames, barSeries);
			BacktestExecutor backtestExecutor = new BacktestExecutor(barSeries);
			List<TradingStatement> tradingStatements = backtestExecutor.execute(strategies, DecimalNum.valueOf(100));
			ratios.add(barSeries.getFirstBar().getClosePrice().doubleValue() / (coinRatios.get(this.coinBarSeriesList.indexOf(barSeries)).getRatio() * initialEquity));
		}
		
		for(String strategyName : strategyNames) {
			List<CashFlow> cashFlows = new ArrayList<>();
			long[] endDates = null;
			
			for(BarSeries barSeries : this.coinBarSeriesList) {
				int currentBarSeriesIndex = this.coinBarSeriesList.indexOf(barSeries);
				double initialRatio = barSeries.getFirstBar().getClosePrice().longValue()/(initialEquity* coinRatios.get(currentBarSeriesIndex).getRatio());
				
				BarSeriesManager barManager = new BarSeriesManager(barSeries);
				TradingRecord tradingRecord = barManager.run(createStrategy(strategyName, barSeries));
				CashFlow cashFlow = new CashFlow(barSeries, tradingRecord);
				
				cashFlows.add(cashFlow);
//				cashFlows.add(new CashFlow(barSeries, tradingRecord));
				if( currentBarSeriesIndex == 0) {
					List<Long> endDatesList = barSeries.getBarData().stream().map(e -> e.getEndTime().toEpochSecond()).collect(Collectors.toCollection(ArrayList::new));
					endDates = endDatesList.stream().mapToLong(u -> u.longValue()).toArray();
				}
			}
			TotalClosePrice totalClosePrice = new TotalClosePrice(this.coinBarSeriesList, ratios);
			CoinCombiCashflowDto coinCombiCashflow = new CoinCombiCashflowDto(coinNames, strategyName, endDates, totalClosePrice.getTotalClosePrice(), new TotalCashFlow(cashFlows, ratios).getTotalCashFlow());
			CoinCombiCashflows.add(coinCombiCashflow);
		}
		
		return CoinCombiCashflows;
	}
}