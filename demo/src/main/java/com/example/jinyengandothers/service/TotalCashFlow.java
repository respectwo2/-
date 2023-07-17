package com.example.jinyengandothers.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ta4j.core.analysis.CashFlow;

public class TotalCashFlow {
    private static final Logger LOG = LoggerFactory.getLogger(TotalCashFlow.class);
	
	private double[] totalCashFlow;
	private List<Double> ratios;
	int index = 0;
	public TotalCashFlow() {}
	
	
	public TotalCashFlow (List<CashFlow> cashFlows, List<Double> ratios) {
		this.ratios = ratios;
		this.totalCashFlow = setTotalCashFlow(cashFlows);
	}

	public double[] getTotalCashFlow() {
		return totalCashFlow;
	}

	public void setTotalCashFlow(double[] totalCashFlow) {
		this.totalCashFlow = totalCashFlow;
	}
	
	private double[] setTotalCashFlow(List<CashFlow> cashFlows) {
		List<Double> totalCashFlowList = new ArrayList<>();
		List<List<Double>> cashFlowsList = new ArrayList<>();
		
		for(CashFlow cashFlow : cashFlows) {
			double ratio = ratios.get(cashFlows.indexOf(cashFlow));
			
			List<Double> cashFlowValues = new ArrayList<>();
			for(int i = 0 ; i < cashFlow.getSize() ; i++) {
				cashFlowValues.add(cashFlow.getValue(i).doubleValue()*ratio);
			}
			cashFlowsList.add(cashFlowValues);
		}
		
		double[][] cashFlows2DArr = cashFlowsList.stream().map(  u  ->  u.stream().mapToDouble(j->j.doubleValue()).toArray()  ).toArray(double[][]::new);
		
		for(int k = 0 ; k < cashFlows2DArr[0].length ; k++) {
			this.index = k;
			totalCashFlowList.add(Arrays.stream(cashFlows2DArr).mapToDouble(a -> a[index]).sum());
		}
		return totalCashFlowList.stream().mapToDouble(i -> i).toArray();
	}
}
