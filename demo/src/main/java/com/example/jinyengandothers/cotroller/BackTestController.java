package com.example.jinyengandothers.cotroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ta4j.core.reports.TradingStatement;

import com.example.jinyengandothers.dao.CoinPriceDao;
import com.example.jinyengandothers.dto.CoinCombiCashflowDto;
import com.example.jinyengandothers.dto.CoinRatioDto;
import com.example.jinyengandothers.service.BackTestingServiceImpl;

@Controller
@RequestMapping("/backtest")
public class BackTestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BackTestController.class);

	
	@Autowired
	CoinPriceDao coinPriceDao;
	
	@GetMapping("")
	public String backTestPage( Model model) {
		model.addAttribute("coins",coinPriceDao.selectAllCoinIdValues());
		return "backtest";
	}
}