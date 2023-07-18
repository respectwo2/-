package com.example.jinyengandothers.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.jinyengandothers.dto.CoinPriceDto;

@Mapper
public interface CoinPriceMapper {
	
	@Select("select distinct coin_ticker from coinprice")
	List<String> selectAllCoinNames();

	@Select("select * from coinprice")
	List<CoinPriceDto> selectAllCoinPrice();
	
	@Select("select end_time, open_price, high_price, low_price, close_price, volume from coinprice where coin_ticker=#{ticker}")
	List<CoinPriceDto> selectCoinAllPrice(@Param("ticker") String coinTicker);
	
	@Select("SELECT coin_id FROM coinprice WHERE coin_ticker = #{ticker} LIMIT 1")
	int selectCoinIdByCoinTicker(@Param("ticker") String coinTicker);


	
	@Select("select count(*) from coinprice where coin_ticker=#{ticker}")
	int getCoinPriceCount(@Param("ticker") String coinName);
	
	@Insert("INSERT INTO coinprice (coin_ticker, coin_id, end_time, open_price, high_price, low_price, close_price, volume, value) " +
	        "VALUES (#{coinPrice.coinTicker}, #{coinPrice.coinId}, #{coinPrice.endTime}, #{coinPrice.openPrice}, #{coinPrice.highPrice}, #{coinPrice.lowPrice}, #{coinPrice.closePrice}, #{coinPrice.volume}, #{coinPrice.value})")
	void insertCoinPrice(@Param("coinPrice") CoinPriceDto coinPrice);

}