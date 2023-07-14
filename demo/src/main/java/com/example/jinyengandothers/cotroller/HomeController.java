package com.example.jinyengandothers.cotroller;

import java.util.LinkedList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.jinyengandothers.dto.CoinWeekChangeDto;
import com.example.jinyengandothers.dao.CoinPriceDao;
import com.example.jinyengandothers.dto.NewsDto;
import com.example.jinyengandothers.service.CryptoCompareNewsService;
import com.example.jinyengandothers.service.CoinInfoService;
import com.example.jinyengandothers.dao.CoinPriceDao;

@Controller
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CoinPriceDao coinPriceDao;
	
	@Autowired
	CryptoCompareNewsService compareNewsService;
	
	@Autowired
	CoinInfoService coinInfoService;
	
	@GetMapping("/1")
	public String index() {
		return "redirect:/main?tvwidgetsymbol=BTC";
	}

	@GetMapping("/test")
	public String realChart(Model model) {
		return "test";
	}

	// test.jsp에서 환율이 정확하지않음.
	@GetMapping("/test2") // 파라미터값 = 티커(https://api.upbit.com/v1/market/all?isDetails=false)
	public String tradingviewTest2(@RequestParam(value = "ticker", defaultValue = "BTC") String ticker, Model model) {
		model.addAttribute("ticker", ticker);
		return "test2";
	}

	@GetMapping("/test3")
	public String test3() {
		return "test3";
	}

	@GetMapping("/coinNews")
	public String test4(Model model, @RequestParam(value = "category", defaultValue = "") String category) { // CryptoCompare
		// news api
		List<NewsDto> newsList = compareNewsService.getNews(category);
		model.addAttribute("newsList", newsList);
		return "coinNews";
	}

	@GetMapping("/weeklyIncreaseRate")
	public String weeklyIncreaseRate(Model model) {
		CoinWeekChangeDto[] coins = coinInfoService.getCoins();
		model.addAttribute("coins", coins);
		return "weeklyIncreaseRate";
	}
	
}
