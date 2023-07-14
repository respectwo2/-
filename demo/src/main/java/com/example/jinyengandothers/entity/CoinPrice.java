package com.example.jinyengandothers.entity;

import lombok.Data;

@Data
public class CoinPrice {
	private String ticker_id;
	private int p_open;
	private int p_high;
	private int p_low;
	private int p_close;
	private int p_volume;
	private int p_value;
	private String p_date;
}
