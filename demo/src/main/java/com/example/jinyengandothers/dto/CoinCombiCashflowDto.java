package com.example.jinyengandothers.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinCombiCashflowDto {
	
	private List<String> coinNames;
	private String strategyName;
	private long[] endDates;
	private double[] combinationPrice;
	private double[] totalCashFlow;
	
	public void setCoinNames(List<String> coinNames) {
		this.coinNames = coinNames;
	}
	
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	
	public void setEndDates(long[] endDates) {
		this.endDates = endDates;
	}
	
	public void setTotalCashFlow(double[] totalCashFlow) {
		this.totalCashFlow = totalCashFlow;
	}	
}