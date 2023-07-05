package com.example.jinyengandothers.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ta4j.core.BacktestExecutor;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Indicator;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.Trade;
import org.ta4j.core.analysis.cost.CostModel;
import org.ta4j.core.analysis.cost.LinearTransactionCostModel;
import org.ta4j.core.analysis.cost.ZeroCostModel;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;
import org.ta4j.core.reports.TradingStatement;
import org.ta4j.core.rules.CrossedUpIndicatorRule;

import com.example.jinyengandothers.dao.CoinPriceDao;
import com.example.jinyengandothers.dto.CoinPrice;

@Service
public class BackTestingSample {
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private CoinPriceDao coinPriceDao;
		
	private BarSeries loadCoinDataTimeSeries(String coin){
		List<CoinPrice> coinPrices = coinPriceDao.selectCoinAllPrice(coin);
		BarSeries coinHistoricalPrices = new BaseBarSeries(coin);
		for(CoinPrice coinPrice: coinPrices) {
			LocalDateTime parsedEndTime = LocalDateTime.parse(coinPrice.getEndTime(),DATE_FORMAT);
			ZonedDateTime zonedDateEndTime = ZonedDateTime.of(parsedEndTime, ZoneId.systemDefault());
			coinHistoricalPrices.addBar(zonedDateEndTime,coinPrice.getOpenPrice(),coinPrice.getHighPrice(),coinPrice.getLowPrice(),coinPrice.getClosePrice(), coinPrice.getVolume());
		 }

		return coinHistoricalPrices;
	}
	
	private Rule createSmaCrossEntryRule(BarSeries series, int shortBarCount, int longBarCount) {
		Indicator<Num> closePrice = new ClosePriceIndicator(series);
		SMAIndicator smaShort = new SMAIndicator(closePrice, shortBarCount);
		SMAIndicator smaLong = new SMAIndicator(closePrice, longBarCount);
		
		return new CrossedUpIndicatorRule(smaShort, smaLong);
	}
	
	private Rule createSmaCrossExitRule(BarSeries series, int shortBarCount, int longBarCount) {
		Indicator<Num> closePrice = new ClosePriceIndicator(series);
		SMAIndicator smaShort = new SMAIndicator(closePrice, shortBarCount);
		SMAIndicator smaLong = new SMAIndicator(closePrice, longBarCount);
		
		return new CrossedUpIndicatorRule(smaShort, smaLong);
	}
	
	private List<Strategy> createStrategies(BarSeries series){
	    List<Strategy> strategies = new ArrayList<>();
	    
	    int barCountStart = 0;
	    int barCountStop = series.getBarCount();
	    int barCountStep = 1;
	    	    
	    for (int shortBarCount = barCountStart; shortBarCount <= barCountStop; shortBarCount += barCountStep) {
	    	for (int longBarCount = shortBarCount + barCountStep; longBarCount <= barCountStop; longBarCount += barCountStep) {
	    		String strategyName = String.format("Sma(%d) CrossOver Sma(%d)",shortBarCount, longBarCount);
	    		strategies.add(new BaseStrategy(strategyName, createSmaCrossEntryRule(series, shortBarCount, longBarCount), createSmaCrossExitRule(series, shortBarCount, longBarCount)));
	    	}
	    }
		return strategies;
	}
	
	public StringBuilder printBackTestResult(String coinName, String strategyName, double slippage, double fee) {
		BarSeries targetSeries = loadCoinDataTimeSeries(coinName);
		List<Strategy> targetStrategies = createStrategies(targetSeries);
		
		double slippageAndFee = slippage+fee;
		CostModel transactionCostModel = new LinearTransactionCostModel(slippageAndFee);
		
		BacktestExecutor backtestExecutor = new BacktestExecutor(targetSeries, transactionCostModel, new ZeroCostModel());
		List<TradingStatement> tradingStatements = backtestExecutor.execute(targetStrategies, DecimalNum.valueOf(50),Trade.TradeType.BUY);
		
		StringBuilder result = new StringBuilder();
		for(TradingStatement t : tradingStatements) {
			result.append(t.getStrategy().getName())
				.append(System.lineSeparator())
				.append(t.getPerformanceReport().getTotalProfitLossPercentage())
				.append(System.lineSeparator())
				.append(t.getPositionStatsReport().getProfitCount())
				.append(System.lineSeparator())
				.append(System.lineSeparator());
		}
		return result;
	}
}
