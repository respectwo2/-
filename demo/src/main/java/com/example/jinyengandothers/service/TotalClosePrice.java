package com.example.jinyengandothers.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ta4j.core.BarSeries;
import org.ta4j.core.analysis.CashFlow;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

public class TotalClosePrice {

	private List<BarSeries> barSeriesList;
	private List<ClosePriceIndicator> closePriceIndicators;
	private double[] totalClosePrice;
	int index;
	
	public TotalClosePrice(List<BarSeries> barSeriesList, List<Double> ratios) {
		closePriceIndicators = new ArrayList<>();
		
		for(BarSeries barSeries : barSeriesList) {
			closePriceIndicators.add(new ClosePriceIndicator(barSeries));		
		}
		
		List<Double> totalClosePriceList = new ArrayList<>();
		List<List<Double>> closePricesList = new ArrayList<>();
		
		for(ClosePriceIndicator closePrices : closePriceIndicators) {
			double ratio = ratios.get(closePriceIndicators.indexOf(closePrices));
			
			List<Double> closePriceValues = new ArrayList<>();
			for(int i = 0 ; i < closePrices.getBarSeries().getBarCount() ; i++) {
				closePriceValues.add(closePrices.getValue(i).doubleValue()*ratio);
			}
			closePricesList.add(closePriceValues);
		}
		
		double[][] closePrices2DArr = closePricesList.stream().map(  u  ->  u.stream().mapToDouble(j->j.doubleValue()).toArray()  ).toArray(double[][]::new);
		
		for(int k = 0 ; k < closePrices2DArr[0].length ; k++) {
			this.index = k;
			totalClosePriceList.add(Arrays.stream(closePrices2DArr).mapToDouble(a -> a[index]).sum());
		}
		
		this.totalClosePrice = totalClosePriceList.stream().mapToDouble(i->i).toArray();		
	}
	
	public double[] getTotalClosePrice() {
		return this.totalClosePrice;
	}
}