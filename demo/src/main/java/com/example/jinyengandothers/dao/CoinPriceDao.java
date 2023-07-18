package com.example.jinyengandothers.dao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ta4j.core.BarSeries;

import com.example.jinyengandothers.dto.CoinPriceDto;
import com.example.jinyengandothers.service.BackTestingServiceImpl;

@Component
public class CoinPriceDao {
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Logger LOG = LoggerFactory.getLogger(CoinPriceDao.class);

	
	@Autowired
	private CoinPriceMapper coinPriceMapper;
	
	public List<String> getAllTickerNames(){
		return coinPriceMapper.selectAllCoinNames();
	}
	
	public List<CoinPriceDto> selectAllCoinPrice(){
		return coinPriceMapper.selectAllCoinPrice();
	}
	
	public List<CoinPriceDto> selectCoinAllPrice(String ticker){
		return coinPriceMapper.selectCoinAllPrice(ticker);
	}
	
	public int selectCoinIdByCoinTicker(String ticker) {
		return coinPriceMapper.selectCoinIdByCoinTicker(ticker);
	}

	public int getCoinPriceCount(String coinName) {
		return coinPriceMapper.getCoinPriceCount(coinName);
	}
	
	public void insertCoin(CoinPriceDto dto) {
		coinPriceMapper.insertCoinPrice(dto);
	}
	
	public List<List<CoinPriceDto>> getSameLengthCoinPrices (List<String> coinNames){
		List<Integer> coinPriceDtoCount = new ArrayList<>();
		List<List<CoinPriceDto>> coinPricesList = new ArrayList<>();
		
		for(String coinName : coinNames) {
			List<CoinPriceDto> coinPrices = selectCoinAllPrice(coinName);
			coinPriceDtoCount.add(coinPrices.size());
			coinPricesList.add(coinPrices);
		}
		
		int max = Arrays.stream(coinPriceDtoCount.stream().mapToInt(i -> i).toArray()).max().getAsInt();
		
		for(List<CoinPriceDto> coinPrices : coinPricesList) {
			
			int currentCoinPrices = coinPricesList.indexOf(coinPrices);
			int currentCoinPriceSize = coinPrices.size();
			
			ZonedDateTime coinPricesFirstDate = ZonedDateTime.of(LocalDateTime.parse(coinPrices.get(0).getEndTime(),DATE_FORMAT), ZoneId.systemDefault()); 
			
			for(int i = 0 ; i < max - currentCoinPriceSize ; i++) {
				coinPrices.add(0,new CoinPriceDto(0, 0, coinNames.get(coinPricesList.indexOf(coinPrices)), coinPricesFirstDate.minusDays(i+1).format(DATE_FORMAT), coinPrices.get(0).getOpenPrice(), coinPrices.get(0).getHighPrice(), coinPrices.get(0).getLowPrice(), coinPrices.get(0).getClosePrice(), 0, 0));
			}
			
			coinPricesList.set(currentCoinPrices, coinPrices);
		}
		
		return coinPricesList;
	}
}