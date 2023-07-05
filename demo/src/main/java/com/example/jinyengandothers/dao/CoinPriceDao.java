package com.example.jinyengandothers.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jinyengandothers.dto.CoinPrice;

@Component
public class CoinPriceDao {
	
	@Autowired
	private CoinPriceMapper coinPriceMapper;
	
	public List<CoinPrice> selectAllCoinPrice(){
		return coinPriceMapper.selectAllCoinPrice();
	}
	
	public List<CoinPrice> selectCoinAllPrice(String ticker){
		return coinPriceMapper.selectCoinAllPrice(ticker);
	}
}
