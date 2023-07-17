package com.example.jinyengandothers.cotroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jinyengandothers.dao.CoinPriceDao;
import com.example.jinyengandothers.dto.CoinCombiCashflowDto;
import com.example.jinyengandothers.dto.CoinRatioDto;
import com.example.jinyengandothers.service.BackTestingServiceImpl;

@RestController
@RequestMapping("/api")
public class BackTestRestController {
	
	@Autowired
	private CoinPriceDao coinPriceDao;
	
	@Autowired
	BackTestingServiceImpl backtesting;
	
	@GetMapping("/tickername")
	public @ResponseBody List<String> getAllTickerNames(){
		return coinPriceDao.getAllTickerNames();
	}
	
	@GetMapping("/chartdata")
	public @ResponseBody ResponseEntity<List<CoinCombiCashflowDto>> getBackTestResult(){
		List<CoinRatioDto> arr = new ArrayList<>();
		List<String> arr2 = new ArrayList<>();

		arr.add(new CoinRatioDto("ETH",0.1));
		arr.add(new CoinRatioDto("ALGO",0.9));
		arr2.add("Bollinger1");
		arr2.add("SMA");
		List<CoinCombiCashflowDto> coinCombinationCashflows = backtesting.getCoinCombinationResult(arr, arr2, 1000000);
		return ResponseEntity.ok(coinCombinationCashflows);
	}
	
}
