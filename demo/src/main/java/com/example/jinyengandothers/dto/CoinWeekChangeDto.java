package com.example.jinyengandothers.dto;

import lombok.Data;

@Data
public class CoinWeekChangeDto  implements Comparable<CoinWeekChangeDto>{
	public double priceChange;
	public String coinName;
	
	public CoinWeekChangeDto(double priceChange, String coinName) {
		this.coinName = coinName;
		this.priceChange = priceChange;
	}
	
	@Override
	public int compareTo(CoinWeekChangeDto o) {
		if(this.priceChange - o.priceChange < 0)
			return 1;
		else if(this.priceChange - o.priceChange == 0)
			return 0;
		else 
			return -1;
	}
}

