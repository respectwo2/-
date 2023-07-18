package com.example.jinyengandothers.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.jinyengandothers.entity.CoinInfo;

@Mapper
public interface CoinInfoMapper {

	@Select("Select * from coininfo where ticker_id =#{tickerId}")
	CoinInfo getCoinInfoById(@Param("tickerId") int tickerId);
	
	@Select("SELECT * FROM coininfo WHERE ticker = #{ticker}")
	CoinInfo getCoinInfoByTicker(@Param("ticker") String ticker);
	
	@Select("SELECT * FROM coininfo WHERE ticker_name = #{tickerName}")
	CoinInfo getCoinInfoByTickerName(@Param("tickerName") String tickerName);

    @Select("SELECT * FROM coininfo WHERE ticker = #{search} OR ticker_name = #{search}")
    CoinInfo getTickerBySearch(@Param("search") String search);
    
    @Select("SELECT ticker FROM coininfo")
    List<String> getAllTicker();
    
    @Select("Select ticker_info from coininfo where ticker = #{search} OR ticker_name = #{search}")
    CoinInfo getTickerInfoByTicker(@Param("ticker") String search);

    @Select("Select ticker_issuer from coininfo where ticker = #{search} OR ticker_name = #{search}")
    CoinInfo getTickerIssuerByTicker(@Param("ticker") String search);

}
