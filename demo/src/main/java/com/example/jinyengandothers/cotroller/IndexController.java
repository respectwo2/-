package com.example.jinyengandothers.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jinyengandothers.entity.CoinInfo;
import com.example.jinyengandothers.service.IndexService;

@Controller
public class IndexController {
	
	@Autowired
	private IndexService indexService;
	
	
	@GetMapping("/main")
	public String showMainPage(@RequestParam("tvwidgetsymbol") String ticker, Model model) {
	    if (ticker.startsWith("UPBIT:") && ticker.endsWith("KRW") && ticker.length() > 10) {
	        ticker = ticker.substring(6, ticker.length() - 3);
	    }
	    model.addAttribute("ticker", ticker);
	    return "main";
	}
	
	@GetMapping("/exchange")
	public String exchangeTicker(@RequestParam("ticker") String ticker, Model model) {
		model.addAttribute("ticker", ticker);
		return "/exchange/ticker";
	}
	
	@GetMapping("/exchange/ticker")
	public String exchangeTickerSearch(Model model) {
		return "index/index";
	}
	
	/*
	 * @GetMapping("/exchange/tickerinfo") public String info(@RequestParam(value =
	 * "ticker_id", required = false) Integer ticker_id,
	 * @RequestParam(value = "ticker", required = false) String ticker,
	 * @RequestParam(value = "ticker_name", required = false) String ticker_name,
	 * Model model) {
	 * 
	 * CoinInfo coinInfo = null;
	 * 
	 * if (ticker_id != null) { coinInfo = indexService.getCoinInfoById(ticker_id);
	 * } else if (ticker != null) { coinInfo =
	 * indexService.getCoinInfoByTicker(ticker); } else if (ticker_name != null) {
	 * coinInfo = indexService.getCoinInfoByTickerName(ticker_name); } else { return
	 * "error"; }
	 * 
	 * if (coinInfo == null && ticker != null) { coinInfo =
	 * indexService.getCoinInfoByTicker(ticker); }
	 * 
	 * if (coinInfo == null && ticker_name != null) { coinInfo =
	 * indexService.getCoinInfoByTickerName(ticker_name); }
	 * 
	 * if (coinInfo == null) { return "검색 결과가 없습니다"; }
	 * 
	 * String retrievedTicker = coinInfo.getTicker(); String retrievedTickerName =
	 * coinInfo.getTicker_name(); String tickerTfeature =
	 * coinInfo.getTicker_tfeature(); String tickerIssuer =
	 * coinInfo.getTicker_issuer();
	 * 
	 * model.addAttribute("ticker_tfeature", tickerTfeature);
	 * model.addAttribute("ticker_name", retrievedTickerName);
	 * model.addAttribute("ticker_issuer", tickerIssuer);
	 * model.addAttribute("ticker", retrievedTicker);
	 * 
	 * return "index/info"; }
	 */
	@GetMapping("/exchange/tickerinfo")
	public String exchangeTickerSearch(@RequestParam("search") String search, Model model) {
	    CoinInfo ticker = indexService.getCoinInfoBySearch(search);
	    
	    if (ticker == null) {
	        return "error/error";
	    }
	    
	    model.addAttribute("search", ticker);
	    return "redirect:/main?tvwidgetsymbol=" + ticker.getTicker();
	}
}
