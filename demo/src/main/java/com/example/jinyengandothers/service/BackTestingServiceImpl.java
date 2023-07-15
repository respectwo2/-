package com.example.jinyengandothers.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ta4j.core.BacktestExecutor;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BarSeriesManager;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Position;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.Trade.TradeType;
import org.ta4j.core.analysis.cost.CostModel;
import org.ta4j.core.analysis.cost.LinearTransactionCostModel;
import org.ta4j.core.analysis.cost.ZeroCostModel;
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
import com.example.jinyengandothers.dto.BackTestResultDto;
import com.example.jinyengandothers.dto.CoinPriceDto;
import com.example.jinyengandothers.dto.CoinRatioDto;
import com.example.jinyengandothers.dto.MarkerDto;


@Service
public class BackTestingServiceImpl{
	
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Logger LOG = LoggerFactory.getLogger(BackTestingServiceImpl.class);
    private BarSeries coinHistoricalPrices;
    public List<Position> coinTrading;    
	
	@Autowired
	private CoinPriceDao coinPriceDao;

	private BarSeries loadCoinDataTimeSeries(List<CoinRatioDto> coinRatios) {
//		List<CoinPriceDto> coinPrices = coinPriceDao.selectCoinAllPrice(coin);
		List<Integer> barCounts = new ArrayList<>();
		
		for(CoinRatioDto coinRatio : coinRatios) {	
			barCounts.add(coinPriceDao.getCoinPriceCount(coinRatio.getCoinName()));
		}

		int max = Arrays.stream(barCounts.stream().mapToInt(i -> i).toArray()).max().getAsInt();
		
		for(CoinRatioDto coinRatio : coinRatios) {
			List<CoinPriceDto> coinPrices = coinPriceDao.selectCoinAllPrice(coinRatio.getCoinName());
			BarSeries coinHistoricalPrices = new BaseBarSeries(coinRatio.getCoinName());
			for(CoinPriceDto coinPrice: coinPrices) {
				LocalDateTime parsedEndTime = LocalDateTime.parse(coinPrice.getEndTime(),DATE_FORMAT);
				ZonedDateTime zonedDateEndTime = ZonedDateTime.of(parsedEndTime, ZoneId.systemDefault());
				coinHistoricalPrices.addBar(zonedDateEndTime,coinPrice.getOpenPrice(),coinPrice.getHighPrice(),coinPrice.getLowPrice(),coinPrice.getClosePrice(), coinPrice.getVolume());
			}
		}
		
		return coinHistoricalPrices;
	}

	private List<Strategy> createStrategies(List<String> strategyNames) {
				
		List<Strategy> strategies = new ArrayList<>();
		BollingerBandFacade bollinger = new BollingerBandFacade(this.coinHistoricalPrices,20,2);
		ClosePriceIndicator closePrice = new ClosePriceIndicator(this.coinHistoricalPrices);
		Rule entryRule, exitRule = null;
		
		for(String strategyName : strategyNames) {
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
//				매수신호: 종가 < 볼린저밴드 하한선
				entryRule = new UnderIndicatorRule(closePrice, bollinger.lower());
//				매도신호: 종가 > 볼린저밴드 상한선 
				exitRule = new OverIndicatorRule(closePrice, bollinger.upper());
				break;
			case "Bollinger2":
//				매수신호: 종가 > 볼린저밴드 상한선
				entryRule = new OverIndicatorRule(closePrice, bollinger.upper());
//				매도신호: 종가 < 볼린저밴드 상한선
				exitRule = new UnderIndicatorRule(closePrice, bollinger.upper());
				break;
			case "VBI":
				entryRule = new InPipeRule(new VolatilityIndicator(this.coinHistoricalPrices), new HighPriceIndicator(this.coinHistoricalPrices), new LowPriceIndicator(this.coinHistoricalPrices));
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
		        StochasticOscillatorKIndicator stochasticOscillK = new StochasticOscillatorKIndicator(this.coinHistoricalPrices, 14); 
		        MACDIndicator macd = new MACDIndicator(closePrice, 9, 26);
		        EMAIndicator emaMacd = new EMAIndicator(macd, 18);
		        entryRule = new OverIndicatorRule(shortEma, longEma).and(new CrossedDownIndicatorRule(stochasticOscillK, 20)).and(new OverIndicatorRule(macd, emaMacd));
		        exitRule = new UnderIndicatorRule(shortEma, longEma).and(new CrossedUpIndicatorRule(stochasticOscillK, 80)).and(new UnderIndicatorRule(macd, emaMacd));
				break;
			case "Supertrend":
				SuperTrendIndicator superTrend = new SuperTrendIndicator(this.coinHistoricalPrices);
				entryRule = new CrossedUpIndicatorRule(closePrice, superTrend.getSuperTrendUpperBandIndicator());
				exitRule = new CrossedDownIndicatorRule(closePrice, superTrend.getSuperTrendUpperBandIndicator());
				break;
			default:
				break;
			}
			strategies.add(new BaseStrategy(strategyName,exitRule, exitRule));
		}
		
		return strategies;
	} 
	
	private List<MarkerDto> setBuyMarkerList(List<Position> positions, BarSeries series) {
		List<MarkerDto> buyMarkers = new ArrayList<>();
		for (Position position : positions) {
            // Buy signal
            buyMarkers.add(new MarkerDto(series.getBar(position.getEntry().getIndex()).getEndTime(), position.getEntry().getNetPrice().getDelegate().longValue()));
        }
		return buyMarkers;
	}
	
	private List<MarkerDto> setSellMarkerList(List<Position> positions, BarSeries series) {
		List<MarkerDto> sellMarkers = new ArrayList<>();
		for (Position position : positions) {
            // sell signal
			sellMarkers.add(new MarkerDto(series.getBar(position.getExit().getIndex()).getEndTime(), position.getExit().getNetPrice().getDelegate().longValue()));
        }
		return sellMarkers;
	}
	
	public List<BackTestResultDto> returnBackTestResult(List<CoinRatioDto> coinRatios, List<String> strategyNames, double initialEquity) {
		double slippageAndFee = 0.013;
		CostModel transactionCostModel = new LinearTransactionCostModel(slippageAndFee);		
		List<BackTestResultDto> backTestResults = new ArrayList<>();
		this.coinHistoricalPrices = loadCoinDataTimeSeries(coinRatios);
		
		for(CoinRatioDto coinRatio : coinRatios) {			
			List<Strategy> strategies = createStrategies(strategyNames);

			BacktestExecutor backtestExecutor = new BacktestExecutor(this.coinHistoricalPrices, transactionCostModel, new ZeroCostModel());
			List<TradingStatement> tradingStatements = backtestExecutor.execute(strategies, DecimalNum.valueOf(100),TradeType.BUY);
			
			for(int i = 0 ; i < strategies.size() ; i++) {
				BackTestResultDto backTestResult = new BackTestResultDto(coinRatio.getCoinName(), coinRatio.getRatio(), strategyNames, this.coinHistoricalPrices.getBarData());
				backTestResult.setPerformanceReport(tradingStatements.get(i).getPerformanceReport());
				backTestResult.setPositionStatsReport(tradingStatements.get(i).getPositionStatsReport());
				BarSeriesManager barManager = new BarSeriesManager(this.coinHistoricalPrices);
				this.coinTrading = barManager.run(strategies.get(i)).getPositions();
				backTestResult.setBuyMarkers(setBuyMarkerList(this.coinTrading,this.coinHistoricalPrices));
				backTestResult.setSellMarkers(setSellMarkerList(this.coinTrading,this.coinHistoricalPrices));
				backTestResults.add(backTestResult);
			}
		}
		
		return backTestResults;
	}
}
