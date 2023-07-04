package com.example.jinyengandothers.service;
import org.springframework.stereotype.Service;
import com.example.jinyengandothers.entity.CoinInfo;

public interface IndexService {

	public CoinInfo getCoinInfoById(int ticker_id); 
	
	public CoinInfo getCoinInfoByTicker(String ticker); 

	public CoinInfo getCoinInfoByTickerName(String ticker_name); 

}
