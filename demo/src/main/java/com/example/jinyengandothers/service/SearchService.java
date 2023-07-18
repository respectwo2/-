package com.example.jinyengandothers.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.jinyengandothers.entity.CoinInfo;

public interface SearchService {

	public CoinInfo getCoinInfoById(int tickerId); 
	
	public CoinInfo getCoinInfoByTicker(String ticker); 

	public CoinInfo getCoinInfoByTickerName(String tickerName); 

	public CoinInfo getCoinInfoBySearch(String search);
	
	public CoinInfo getTickerInfoByTicker(String search);
	
	public CoinInfo getTickerIssuerByTicker(String search);
	
	public List<String> getAllCoinTicker();
}
