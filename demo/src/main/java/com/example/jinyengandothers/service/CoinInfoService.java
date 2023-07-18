package com.example.jinyengandothers.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.jinyengandothers.dto.CoinWeekChangeDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoinInfoService {

	@Autowired
	SearchServiceImpl searchServiceImpl; // CoinInfoMapper

	private CoinWeekChangeDto[] coins;

	public CoinWeekChangeDto[] getCoins() { // 주간 상승 수집 데이터
		return coins;
	}

	private CoinWeekChangeDto[] coins24Acc;

	public CoinWeekChangeDto[] getCoins24Acc() {
		return coins24Acc;
	}

	private String currentTime = "";

	public String getCurrentTime() {
		return currentTime;
	}

	public List<String> getAlltickers() {
		return searchServiceImpl.getAllCoinTicker();
	}

	public CoinWeekChangeDto[] getWeeklyIncreaseRate() {
		// Get all coin ticker names
		List<String> coinNames = getAlltickers();

		String candleUrl = "https://api.upbit.com/v1/candles/minutes/10";

		HttpClient httpClient = HttpClient.newHttpClient();

		coins = new CoinWeekChangeDto[coinNames.size()];

		int idx = 0;
		for (String coinName : coinNames) {
			double nowPrice = 0, oneWeekAgoPrice = 0;
			coinName = "KRW-" + coinName;

			// Get current price
			HttpRequest currentPriceRequest = HttpRequest.newBuilder()
					.uri(URI.create(candleUrl + "?market=" + coinName + "&count=1")).GET().build();
			try {
				HttpResponse<String> currentPriceResponse = httpClient.send(currentPriceRequest,
						HttpResponse.BodyHandlers.ofString());
				if (currentPriceResponse.statusCode() == 200) {
					String response = currentPriceResponse.body();
					ObjectMapper objectMapper = new ObjectMapper();
					JsonNode jsonNode = objectMapper.readTree(response);
					JsonNode firstObject = jsonNode.get(0);
					nowPrice = firstObject.get("trade_price").asDouble();
					currentTime = firstObject.get("candle_date_time_kst").asText();
				} else {
					log.warn("Failed to fetch price from one week ago for " + coinName + ". Status code: "
							+ currentPriceResponse.statusCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Get price from one week ago
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime oneWeekAgo = now.minusWeeks(1);
			ZonedDateTime zonedOneWeekAgo = ZonedDateTime.of(oneWeekAgo, ZoneId.systemDefault());
			ZoneOffset offset = zonedOneWeekAgo.getOffset();
			String oneWeekAgoDate = (zonedOneWeekAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
					+ offset).replace(":", "%3A").replace("+", "%2B");
			HttpRequest oneWeekAgoPriceRequest = HttpRequest.newBuilder()
					.uri(URI.create(candleUrl + "?market=" + coinName + "&count=1&to=" + oneWeekAgoDate)).GET().build();
			try {
				HttpResponse<String> oneWeekAgoPriceResponse = httpClient.send(oneWeekAgoPriceRequest,
						HttpResponse.BodyHandlers.ofString());
				if (oneWeekAgoPriceResponse.statusCode() == 200) {
					String response = oneWeekAgoPriceResponse.body();
					ObjectMapper objectMapper = new ObjectMapper();
					JsonNode jsonNode = objectMapper.readTree(response);
					JsonNode firstObject = jsonNode.get(0);
					oneWeekAgoPrice = firstObject.get("trade_price").asDouble();
				} else {
					log.warn("Failed to fetch price from one week ago for " + coinName + ". Status code: "
							+ oneWeekAgoPriceResponse.statusCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			coins[idx++] = new CoinWeekChangeDto(((nowPrice - oneWeekAgoPrice) / oneWeekAgoPrice * 100), coinName);

			// Delay between API requests
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		Arrays.sort(coins);
		log.info("주간 상승률 수집");
		return coins;
	}

	public CoinWeekChangeDto[] getCoinDailyVolume() {
		// Get all coin ticker names
		List<String> coinNames = getAlltickers();

		String url = "https://api.upbit.com/v1/ticker";

		HttpClient httpClient = HttpClient.newHttpClient();

		CoinWeekChangeDto[] coins24Acc = new CoinWeekChangeDto[coinNames.size()];

		int idx = 0;
		for (String coinName : coinNames) {
			coinName = "KRW-" + coinName;
			double accTradeVolume24h = 0;
			HttpRequest currentPriceRequest = HttpRequest.newBuilder().uri(URI.create(url + "?markets=" + coinName))
					.GET().build();
			try {
				HttpResponse<String> currentPriceResponse = httpClient.send(currentPriceRequest,
						HttpResponse.BodyHandlers.ofString());
				if (currentPriceResponse.statusCode() == 200) {
					String response = currentPriceResponse.body();
					ObjectMapper objectMapper = new ObjectMapper();
					JsonNode jsonNode = objectMapper.readTree(response);
					JsonNode firstObject = jsonNode.get(0);
					accTradeVolume24h = firstObject.get("acc_trade_volume_24h").asDouble();
//					currentTime = firstObject.get("candle_date_time_kst").asText();
				} else {
					log.warn("Failed to fetch price from 24 acc volume  for " + coinName + ". Status code: "
							+ currentPriceResponse.statusCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			coins24Acc[idx++] = new CoinWeekChangeDto(accTradeVolume24h, coinName);

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		Arrays.sort(coins24Acc);
		log.info("24시간 누적거래량 수집");
		return coins24Acc;
	}

	@Scheduled(cron = "0 */1 * * * *") // Run every 1 minutes
	public void updateCoinDailyVolume() {
		coins24Acc = getCoinDailyVolume();
	}


	@Scheduled(cron = "0 */10 * * * *") // Run every 10 minutes
	public void updateCoinWeekChange() {
		coins = getWeeklyIncreaseRate();
	}

}
