package com.example.jinyengandothers.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.jinyengandothers.dto.CoinIdValueDto;
import com.example.jinyengandothers.dto.CoinPriceDto;

@Mapper
public interface CoinPriceMapper {
	
	@Select("select distinct coin_id, coin_ticker from coinprice")
	List<CoinIdValueDto> selectAllCoinIdValues();
	
	@Select("select distinct coin_id, coin_ticker from coinprice")
	List<String> selectAllCoinNames();

	@Select("select * from coinprice")
	List<CoinPriceDto> selectAllCoinPrice();
	
	@Select("select end_time, open_price, high_price, low_price, close_price, volume from coinprice where coin_ticker=#{ticker}")
	List<CoinPriceDto> selectCoinAllPrice(@Param("ticker") String coinTicker);
	
	@Select("select count(*) from coinprice where coin_ticker=#{ticker}")
	int selectCoinPriceCount(@Param("ticker") String coinName);
}
