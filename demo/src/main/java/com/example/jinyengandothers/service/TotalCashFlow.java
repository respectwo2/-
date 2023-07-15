package com.example.jinyengandothers.service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.Position;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.analysis.CashFlow;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.num.Num;

public class TotalCashFlow {
	
	private long[] endDates;
	private long[] totalCashFlow;
	int index = 0;
	public TotalCashFlow() {}
	
	public BarSeries addZeroBars(int max, int currentBarCount, List<Bar> barList) {
		ZonedDateTime startDate = barList.get(0).getEndTime();
		
		for(int i = 0 ; i < max - currentBarCount ; i++) {
			barList.add(new BaseBar(Duration.ofDays(1), startDate.minusDays(i+1), DoubleNum.ZERO, DoubleNum.ZERO, DoubleNum.ZERO, DoubleNum.ZERO, DoubleNum.ZERO, DoubleNum.ZERO, 0));
		}
		
		return new BaseBarSeries(barList);
	}
	
	public TotalCashFlow (List<CashFlow> cashFlows) {
		List<Long> totalCashFlowList = new ArrayList<>();
		List<List<Long>> cashFlowsList = new ArrayList<>();
		
		for(CashFlow cashFlow : cashFlows) {
			List<Long> cashFlowValues = new ArrayList<>();
			for(int i = 0 ; i < cashFlow.getSize() ; i++) {
				cashFlowValues.add(cashFlow.getValue(i).longValue());
			}
			cashFlowsList.add(cashFlowValues);
		}
		
		long[][] cashFlows2DArr = cashFlowsList.stream().map(  u  ->  u.stream().mapToLong(j->j.longValue()).toArray()  ).toArray(long[][]::new);
		
		for(int k = 0 ; k < cashFlows2DArr[0].length ; k++) {
			this.index = k;
			totalCashFlowList.add(Arrays.stream(cashFlows2DArr).mapToLong(a -> a[index]).sum());
		}
		
		this.totalCashFlow = totalCashFlowList.stream().mapToLong(u -> u).toArray();
	}	
}
