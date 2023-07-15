package com.example.jinyengandothers.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;

@Data
public class CoinPriceDto {
	
	private int coinPriceId, coinId;
	private String coinTicker;
	private String endTime;
	private double openPrice, highPrice, lowPrice, closePrice, volume, value;
}

//create table coin_price(
//coin_price_id int auto_increment,
//coin_id int,
//coin_ticker varchar2(10),
//end_time varchar2(50),
//open_price double,
//high_price double,
//low_price double,
//close_price double,
//volume double,
//value double,
//primary key(coin_price_id)
//);