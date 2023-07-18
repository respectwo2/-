package com.example.jinyengandothers.Scheduler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.jinyengandothers.dao.CoinPriceDao;
import com.example.jinyengandothers.dto.CoinPriceDto;
import com.example.jinyengandothers.service.SearchServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoinPriceSchduler {

	@Autowired
	SearchServiceImpl searchServiceImpl; // CoinInfoMapper

	@Autowired
	CoinPriceDao dao;

	@Scheduled(cron = "0 9 * * * *")
	public void updateCoinpriceTable() {
		List<String> tickers = searchServiceImpl.getAllCoinTicker();

		String candleUrl = "https://api.upbit.com/v1/candles/days";

		HttpClient httpClient = HttpClient.newHttpClient();
		int testidx = 1;
		for (String ticker : tickers) {
			ticker = "KRW-"+ticker;
			LocalDateTime now = LocalDateTime.now();
			ZonedDateTime nowZoned = ZonedDateTime.of(now, ZoneId.systemDefault());
			ZoneOffset offset = nowZoned.getOffset();
			String nowDate = ((nowZoned.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"T00:00:00")
					+ offset).replace(":", "%3A").replace("+", "%2B");
			HttpRequest oneDayAgoRequest = HttpRequest.newBuilder()
					.uri(URI.create(candleUrl + "?market=" + ticker + "&count=1&to=" + nowDate)).GET().build();

			try {
				HttpResponse<String> currentPriceResponse = httpClient.send(oneDayAgoRequest,
						HttpResponse.BodyHandlers.ofString());
				if (currentPriceResponse.statusCode() == 200) {
					String response = currentPriceResponse.body();
					ObjectMapper objectMapper = new ObjectMapper();
					JsonNode jsonNode = objectMapper.readTree(response);
					JsonNode firstObject = jsonNode.get(0);
					String coinTicker = ticker.replace("KRW-", "");
					int CoinId = dao.selectCoinIdByCoinTicker(coinTicker);
				
					String endTime = firstObject.get("candle_date_time_kst").asText();
					double openPrice = firstObject.get("opening_price").asDouble(); 
					double highPrice = firstObject.get("high_price").asDouble(); 
					double lowPrice = firstObject.get("low_price").asDouble(); 
					double closePrice = firstObject.get("trade_price").asDouble(); 
					double volume = firstObject.get("candle_acc_trade_volume").asDouble(); 
					double value = firstObject.get("candle_acc_trade_price").asDouble(); 
					
					CoinPriceDto dto = new CoinPriceDto();
					dto.setEndTime(endTime.replace("T", " "));
					dto.setClosePrice(closePrice);
					dto.setCoinId(CoinId);
					dto.setCoinTicker(coinTicker);
					dto.setHighPrice(highPrice);
					dto.setLowPrice(lowPrice);
					dto.setOpenPrice(openPrice);
					dto.setValue(value);
					dto.setVolume(volume);
					
					dao.insertCoin(dto);
					
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					log.info(endTime + " : " + coinTicker + "데이터수집");
				} else {
					log.warn("Failed to fetch data from one day ago for " + ticker + ". Status code: "
							+ currentPriceResponse.statusCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
