package com.example.jinyengandothers.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.jinyengandothers.entity.CoinInfo;

@Mapper
public interface CoinInfoMapper {

	@Select("Select * from coininfo where ticker_id =#{ticker_id}")
	CoinInfo getCoinInfoById(@Param("ticker_id") int ticker_id);
	
	@Select("SELECT * FROM coininfo WHERE ticker = #{ticker}")
	CoinInfo getCoinInfoByTicker(@Param("ticker") String ticker);
	
	@Select("SELECT * FROM coininfo WHERE ticker_name = #{ticker_name}")
	CoinInfo getCoinInfoByTickerName(@Param("ticker_name") String ticker_name);

}
