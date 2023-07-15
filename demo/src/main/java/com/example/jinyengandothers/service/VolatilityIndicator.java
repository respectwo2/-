package com.example.jinyengandothers.service;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.num.Num;

public class VolatilityIndicator extends CachedIndicator<Num>{

	protected VolatilityIndicator(BarSeries series) {
		super(series);
	}

	@Override
	protected Num calculate(int index) {
		Num volatility = index == 0 ? DecimalNum.valueOf(0) : getBarSeries().getBar(index-1).getHighPrice().minus(getBarSeries().getBar(index-1).getLowPrice());
		Num goalPrice = (index == 0 || index >= getBarSeries().getBarCount()-1) ? getBarSeries().getBar(index).getOpenPrice() : getBarSeries().getBar(index).getOpenPrice().plus(volatility.multipliedBy(DecimalNum.valueOf(0.5)));
		return goalPrice;
	}

	@Override
	public int getUnstableBars() {
		return 0;
	}
}
