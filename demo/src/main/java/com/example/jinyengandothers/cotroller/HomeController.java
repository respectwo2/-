package com.example.jinyengandothers.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "/index";
	}

	@GetMapping("/test")
	public String realChart(Model model ) {
		return "test";
	}
	
	// test.jsp에서 환율이 정확하지않음. 
	@GetMapping("/test2") //파라미터값 = 티커(https://api.upbit.com/v1/market/all?isDetails=false)
	public String tradingviewTest2(@RequestParam(value= "ticker", defaultValue = "BTC") String ticker, Model model) {
		model.addAttribute("ticker", ticker);
		return "test2";
	}
}
