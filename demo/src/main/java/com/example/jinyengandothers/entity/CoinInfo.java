package com.example.jinyengandothers.entity;

import lombok.Data;

@Data
public class CoinInfo {
	private int ticker_id;
	private String ticker;
	private String ticker_name;
	private String ticker_issuer;
	private String ticker_tfeature;
	private String ticker_info;
}