package com.example.jinyengandothers.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;import org.slf4j.helpers.CheckReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.jinyengandothers.dto.NewsDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CryptoCompareNewsService {

	public static String apiKey = "08ef7bd6e506083444b129d43f1ee6db62a6d145558d9b2ee68835bd32789a41";

	public List<NewsDto> getNews(String catgories) {
		List<NewsDto> newsList = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();
		String apiUrl = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN&categories=" + catgories + "&api_key="
				+ apiKey;

		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			String response = responseEntity.getBody();

			try {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(response);
				JsonNode dataNode = rootNode.get("Data");
				if (dataNode != null && dataNode.isArray()) {
					for (JsonNode newsNode : dataNode) {
						NewsDto newsDto = new NewsDto();
						newsDto.setTitle(newsNode.get("title").asText());
						String body = newsNode.get("body").asText();
						if (body.length() > 500) {
							body = body.substring(0, 499);
						}
						newsDto.setBody(body);
						newsDto.setArticleUrl(newsNode.get("url").asText());
						newsDto.setImgUrl(newsNode.get("imageurl").asText());
						newsDto.setCategories(newsNode.get("categories").asText());
						newsDto.setSource(newsNode.get("source_info").get("name").asText());
						String koreanTime = convertToKoreanTime(newsNode.get("published_on").asText());
						newsDto.setTime(koreanTime);
						newsList.add(newsDto);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			log.info(responseEntity.getStatusCode().toString());
		}

		return newsList;
	}

	public static String convertToKoreanTime(String timestamp) {
		long millis = Long.parseLong(timestamp);

		ZonedDateTime utcDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(millis), ZoneId.of("UTC"))
				.atZone(ZoneId.of("UTC"));

		ZonedDateTime koreanDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.KOREA);
		return koreanDateTime.format(formatter);
	}
}
