package com.example.jinyengandothers.cotroller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jinyengandothers.dao.CoinPriceDao;
import com.example.jinyengandothers.dto.BacktestVariableDto;
import com.example.jinyengandothers.dto.CoinCombiCashflowDto;
import com.example.jinyengandothers.dto.CoinIdValueDto;
import com.example.jinyengandothers.dto.CoinRatioDto;
import com.example.jinyengandothers.service.BackTestingServiceImpl;

@RestController
@RequestMapping("/api")
public class BackTestRestController {
	private static final Logger LOG = LoggerFactory.getLogger(BackTestRestController.class);

	
	@Autowired
	private CoinPriceDao coinPriceDao;
	
	@Autowired
	BackTestingServiceImpl backtesting;
	
	@GetMapping("/tickername")
	public @ResponseBody List<String> getAllTickerNames(){
		return coinPriceDao.selectAllTickerNames();
	}
	
	@GetMapping("/cointagbox")
	public @ResponseBody ResponseEntity<List<CoinIdValueDto>> getCoinIdValues(){
		return ResponseEntity.ok(coinPriceDao.selectAllCoinIdValues());
	} 
	
	@PostMapping("/chartdata")
	public @ResponseBody ResponseEntity<List<CoinCombiCashflowDto>> getBackTestResult(@RequestBody BacktestVariableDto backtestVariable){
		List<CoinRatioDto> coins = new ArrayList<>();
		List<String> strategies = new ArrayList<>();

		for(int i = 0 ; i < backtestVariable.getCoins().size() ; i++) {
			coins.add(new CoinRatioDto(backtestVariable.getCoins().get(i), backtestVariable.getRatios().get(i)));
		}
		strategies.addAll(backtestVariable.getStrategies());
		
//		arr.add(new CoinRatioDto("BTC",0.1));
//		arr.add(new CoinRatioDto("ALGO",0.9));
//		arr2.add("Bollinger1");
//		arr2.add("SMA");
		List<CoinCombiCashflowDto> coinCombinationCashflows = backtesting.getCoinCombinationResult(coins, strategies, 1000000);
		return ResponseEntity.ok(coinCombinationCashflows);
	}
	
}
