package com.example.jinyengandothers.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.jinyengandothers.dto.NewsDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoinService {

	@Autowired
	IndexServiceImpl indexServiceImpl; // CoinInfoMapper

	public List<String> getWeeklyIncreaseRate() {
		// 모든 티커 이름 정보 가져오기.
		List<String> coinNames = indexServiceImpl.getAllCoinTicker();

		// url end point:
		// https://api.upbit.com/v1/candles/days?to=2023-07-06T00%3A00%3A00&count=1&market=KRW-BTC
		String onedayCandleUrl = "https://api.upbit.com/v1/candles/days?&count=1&market=";

		LocalDate now = LocalDate.now();
		String oneWeekAgoDate = now.minusWeeks(1).toString() + "T09:00:00";
		RestTemplate restTemplate = new RestTemplate();

		int[] prices = new int[coinNames.size()];

		int idx = 1;
		for (String coinName : coinNames) {
			if (idx % 5 == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			double nowPrice = 0, oneWeekAgoPrice = 0;

			restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.exchange(onedayCandleUrl + "KRW-" + coinName,
					HttpMethod.GET, null, String.class);
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				String response = responseEntity.getBody();
				ObjectMapper objectMapper = new ObjectMapper();

				try {
					JsonNode jsonNode = objectMapper.readTree(response);
					JsonNode firstObject = jsonNode.get(0);
					nowPrice = firstObject.get("opening_price").asDouble();
				} catch (Exception e) {
					// Handle any exceptions that occur during parsing
					e.printStackTrace();
				}
			} else {
				System.out.println(responseEntity.getStatusCode());
			}
			restTemplate = new RestTemplate();
			responseEntity = restTemplate.exchange(onedayCandleUrl + "KRW-" + coinName + "&to=" + oneWeekAgoDate,
					HttpMethod.GET, null, String.class);
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				String response = responseEntity.getBody();
				ObjectMapper objectMapper = new ObjectMapper();

				try {
					JsonNode jsonNode = objectMapper.readTree(response);
					JsonNode firstObject = jsonNode.get(0);
					oneWeekAgoPrice = firstObject.get("opening_price").asDouble();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(responseEntity.getStatusCode());
			}
			idx += 1;
			System.out.println(coinName + (oneWeekAgoPrice - nowPrice));
		}

		return coinNames;
	}

}
