package com.example.jinyengandothers.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.jinyengandothers.dto.CoinPrice;

@Mapper
public interface CoinPriceMapper {

	@Select("select * from coin_price")
	List<CoinPrice> selectAllCoinPrice();
	
	@Select("select end_time, open_price, high_price, low_price, close_price, volume from coin_price where coin_ticker=#{ticker}")
	List<CoinPrice> selectCoinAllPrice(@Param("ticker") String coinTicker);
	
}
