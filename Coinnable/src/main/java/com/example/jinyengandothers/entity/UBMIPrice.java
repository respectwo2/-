package com.example.jinyengandothers.entity;

import lombok.Data;

@Data
public class UBMIPrice {
	int indexPriceId;
	String candleDateTime;
	double openingPrice;
	double highPrice;
	double tradePrice;
	
}
