//package com.example.jinyengandothers.cotroller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.example.jinyengandothers.entity.CoinInfo;
//import com.example.jinyengandothers.service.IndexService;
//
//@Controller
//public class IndexController {
//	
//	@Autowired
//	private IndexService indexService;
//	
//	
//	@GetMapping("/main")
//	public String showMainPage(@RequestParam("tvwidgetsymbol") String ticker, Model model) {
//	    if (ticker.startsWith("UPBIT:") && ticker.endsWith("KRW") && ticker.length() > 10) {
//	        ticker = ticker.substring(6, ticker.length() - 3);
//	    }
//	    model.addAttribute("ticker", ticker);
//	    return "main";
//	}
//	
//	@GetMapping("/exchange")
//	public String exchangeTicker(@RequestParam("ticker") String ticker, Model model) {
//		model.addAttribute("ticker", ticker);
//		return "/exchange/ticker";
//	}
//	
//	@GetMapping("/exchange/ticker")
//	public String exchangeTickerSearch(Model model) {
//		return "index/index";
//	}
//	
//	@GetMapping("/exchange/tickerinfo")
//	public String exchangeTickerSearch(@RequestParam("search") String search, Model model) {
//	    CoinInfo ticker = indexService.getCoinInfoBySearch(search);
//	    
//	    if (ticker == null) {
//	        return "error/error";
//	    }
//	    
//	    model.addAttribute("search", ticker);
//	    return "redirect:/main?tvwidgetsymbol=" + ticker.getTicker();
//	}
//}
