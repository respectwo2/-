package com.example.jinyengandothers.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jinyengandothers.dto.CoinPriceDto;

@Component
public class CoinPriceDao {
	
	@Autowired
	private CoinPriceMapper coinPriceMapper;
	
	public List<CoinPriceDto> selectAllCoinPrice(){
		return coinPriceMapper.selectAllCoinPrice();
	}
	
	public List<CoinPriceDto> selectCoinAllPrice(String ticker){
		return coinPriceMapper.selectCoinAllPrice(ticker);
	}

	public int getCoinPriceCount(String coinName) {
		return coinPriceMapper.getCoinPriceCount(coinName);
	}
}
