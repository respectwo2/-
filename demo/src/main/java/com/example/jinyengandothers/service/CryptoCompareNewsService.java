package com.example.jinyengandothers.service;

import java.util.ArrayList;
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
						newsList.add(newsDto);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(responseEntity.getStatusCode());
		}

		return newsList;
	}
}
