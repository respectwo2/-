package com.example.jinyengandothers.dto;

import java.util.ArrayList;
import java.util.List;

import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Position;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;
import org.ta4j.core.reports.PerformanceReport;
import org.ta4j.core.reports.PositionStatsReport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BackTestResultDto {
	
	private String ticker;
	private String tickerName;
	private List<String> strategyNames;
	private double tickerRatio;
	private List<long[]> barSeries;
	private List<List<MarkerDto>> buyMarkers;
	private List<List<MarkerDto>> sellMarkers;
	private List<long[]> positionStatsReports;
	private List<long[]> performanceReports;
	private long profitCount, lossCount, breakEvenCount;
	private long totalProfitLoss, totalProfitLossPercentage, totalProfit, totalLoss;
	
	public BackTestResultDto(String ticker, double tickerRatio, List<String> strategyNames, List<Bar> barSeries) {
		super();
		this.ticker = ticker;
		this.tickerRatio = tickerRatio;
		this.strategyNames = strategyNames;
		List<long[]> realBarDatas = new ArrayList<>();
		for (Bar bar : barSeries) {
			long[] barData = {bar.getEndTime().toEpochSecond(), bar.getOpenPrice().longValue(), bar.getHighPrice().longValue(), 
				bar.getLowPrice().longValue(), bar.getClosePrice().longValue()};
			realBarDatas.add(barData);
		}
		this.barSeries = realBarDatas;
	}

	public String getTicker() {
		return ticker;
	}

	public String getTickerName() {
		return tickerName;
	}

	public List<String> getStrategyNames() {
		return strategyNames;
	}

	public double getTickerRatio() {
		return tickerRatio;
	}

	public List<long[]> getBarSeries() {
		return barSeries;
	}

	public List<List<MarkerDto>> getBuyMarkers() {
		return buyMarkers;
	}

	public List<List<MarkerDto>> getSellMarkers() {
		return sellMarkers;
	}

	public long getProfitCount() {
		return profitCount;
	}

	public long getLossCount() {
		return lossCount;
	}

	public long getBreakEvenCount() {
		return breakEvenCount;
	}


	public long getTotalProfitLoss() {
		return totalProfitLoss;
	}

	public long getTotalProfitLossPercentage() {
		return totalProfitLossPercentage;
	}

	public long getTotalProfit() {
		return totalProfit;
	}

	public long getTotalLoss() {
		return totalLoss;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public void setTickerName(String tickerName) {
		this.tickerName = tickerName;
	}

	public void setStrategyName(List<String> strategyNames) {
		this.strategyNames = strategyNames;
	}

	public void setTickerRatio(double tickerRatio) {
		this.tickerRatio = tickerRatio;
	}

	public void setBarSeries(List<long[]> barSeries) {
		this.barSeries = barSeries;
	}

	public void setBuyMarkers(List<List<MarkerDto>> buyMarkers) {
		this.buyMarkers = buyMarkers;
	}

	public void setSellMarkers(List<List<MarkerDto>> sellMarkers) {
		this.sellMarkers = sellMarkers;
	}

	public void setPositionStatsReport(PositionStatsReport positionStatsReport) {
		this.profitCount = positionStatsReport.getProfitCount().longValue();
		this.lossCount = positionStatsReport.getLossCount().longValue();
		this.breakEvenCount = positionStatsReport.getBreakEvenCount().longValue();
	}

	public void setProfitCount(long profitCount) {
		this.profitCount = profitCount;
	}

	public void setLossCount(long lossCount) {
		this.lossCount = lossCount;
	}

	public void setBreakEvenCount(long breakEvenCount) {
		this.breakEvenCount = breakEvenCount;
	}

	public void setPerformanceReport(PerformanceReport performanceReport) {
		this.totalProfitLoss = performanceReport.getTotalProfitLoss().longValue();
		this.totalProfitLossPercentage = performanceReport.getTotalProfitLossPercentage().longValue();
		this.totalProfit = performanceReport.getTotalProfit().longValue();
		this.totalLoss = performanceReport.getTotalProfitLossPercentage().longValue();
	}

	public void setTotalProfitLoss(long totalProfitLoss) {
		this.totalProfitLoss = totalProfitLoss;
	}

	public void setTotalProfitLossPercentage(long totalProfitLossPercentage) {
		this.totalProfitLossPercentage = totalProfitLossPercentage;
	}

	public void setTotalProfit(long totalProfit) {
		this.totalProfit = totalProfit;
	}

	public void setTotalLoss(long totalLoss) {
		this.totalLoss = totalLoss;
	}	
}