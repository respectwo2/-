package com.example.jinyengandothers.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jinyengandothers.dto.CoinWeekChangeDto;
import com.example.jinyengandothers.dto.NewsDto;
import com.example.jinyengandothers.entity.CoinInfo;
import com.example.jinyengandothers.service.CoinInfoService;
import com.example.jinyengandothers.service.CryptoCompareNewsService;
import com.example.jinyengandothers.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	CoinInfoService coinInfoService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private CryptoCompareNewsService compareNewsService;
	
	
	@GetMapping(value = {"/main", "/"})
	public String showMainPage(@RequestParam(value="tvwidgetsymbol", defaultValue = "BTC") String ticker, Model model) {
	    if (ticker.startsWith("UPBIT:") && ticker.endsWith("KRW") && ticker.length() > 10) {
	        ticker = ticker.substring(6, ticker.length() - 3);
	    }
	    
	    CoinInfo ticker1 = searchService.getCoinInfoBySearch(ticker);
	    
	    if (ticker1 == null) {
	        return "error/error";
	    }
	    
	    String tickerinfo = ticker1.getTickerInfo();
	    String tickerissuer = ticker1.getTickerIssuer();

	    model.addAttribute("info",tickerinfo);
	    model.addAttribute("issuer",tickerissuer);
	    model.addAttribute("ticker", ticker);
	    
	    return "main";
	}
	
	@GetMapping("/tickerinfo")
	public String exchangeTickerSearch(@RequestParam("search") String search, Model model) {
	    CoinInfo ticker = searchService.getCoinInfoBySearch(search);
	    
	    if (ticker == null) {
	        return "error/error";
	    }
	    
	    model.addAttribute("search", ticker);
	    return "redirect:/main?tvwidgetsymbol=" + ticker.getTicker();
	}
	
	@GetMapping("/trend")
	public String weeklyIncreaseRate(Model model) {
		CoinWeekChangeDto[] coins = coinInfoService.getCoins();
		String currentTime = coinInfoService.getCurrentTime();
		if(!currentTime.isEmpty()) currentTime = currentTime.replace("T", " ").substring(0,16);
		model.addAttribute("coins", coins);
		model.addAttribute("currentTime", currentTime);
		return "trend";
	}
	
	@GetMapping("/coinNews")
	public String test4(Model model, @RequestParam(value = "category", defaultValue = "") String category) { // CryptoCompare
		// news api
		List<NewsDto> newsList = compareNewsService.getNews(category);
		model.addAttribute("newsList", newsList);
		return "coinNews";
	}}
