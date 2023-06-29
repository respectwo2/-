package com.example.jinyengandothers.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jinyengandothers.entity.CoinInfo;

@Controller
public class IndexController {

	
	@GetMapping("/main")
	public String main(Model model){
		return "/index/index";
		
	}
	@GetMapping("/exchange")
	public String exchangeTicker(@RequestParam("ticker") String ticker, Model model) {
		model.addAttribute("ticker", ticker);
		return "/exchange/ticker";
	}
	
	@GetMapping("/exchange/ticker")
	public String exchangeTickerSearch(Model model) {
        CoinInfo coinInfo = new CoinInfo();
//        coinInfo.setTicker_id(1);
//        coinInfo.setTicker("BTC");
//        coinInfo.setTicker_name("Bitcoin");
//        coinInfo.setTicker_issuer("Bitcoin Issuer");
//        coinInfo.setTicker_tfeature("Bitcoin TFeature");
//        coinInfo.setTicker_info("Bitcoin Info");
		return null;

	}
}
