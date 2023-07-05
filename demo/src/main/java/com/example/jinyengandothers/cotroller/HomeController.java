package com.example.jinyengandothers.cotroller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jinyengandothers.dao.CoinPriceDao;
import com.example.jinyengandothers.service.BackTestingSample;


@Controller
public class HomeController {
	
	@Autowired
	private CoinPriceDao coinPriceDao;
	
	@Autowired
	private BackTestingSample bts;
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("ticker","BTC");
		
		System.out.println(bts.printBackTestResult("ALGO", "", 0.001, 0.005).toString());
		return "main";
	}
	
//	@GetMapping("/main")
//    public String showMainPage(@RequestParam("tvwidgetsymbol") String ticker, Model model) {
//		if(ticker.length() > 3) {
//			ticker = ticker.substring(6,9);
//		}
//        model.addAttribute("ticker", ticker);
//        return "main";
//    }
	
	@GetMapping("/{ticker_name}")
	public String index1(Model model, @PathVariable("ticker_name")String ticker_name) {
		
		model.addAttribute(ticker_name);
		
		return "main";
	}
	
}
