package com.example.jinyengandothers.cotroller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.jinyengandothers.dto.NewsDto;
import com.example.jinyengandothers.service.CryptoCompareNewsService;


@Controller
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	private CoinPriceDao coinPriceDao;
	
	@Autowired
	private BackTestingSample bts;
	
	@Autowired
	private BackTestingSample2 bts2;
	
	@Autowired
	CryptoCompareNewsService compareNewsService;
	
	@GetMapping("/1")
	public String index() {
		return "redirect:/main?tvwidgetsymbol=BTC";
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
	
	@GetMapping("/test3")
	public String test3() {
		return "test3";
	}
	
	@GetMapping("/test43")
	public String test4(Model model, @RequestParam(value= "ticker", defaultValue = "BTC") String ticker) { //CryptoCompare news api
		List<NewsDto> newsList =  compareNewsService.getNews(ticker);
		model.addAttribute("newsList", newsList);
		return "testt4";
	}
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("ticker","BTC");
//		LOG.info(bts.printBackTestResult("ALGO", "", 0.001, 0.005).toString());
		bts2.runPython();
		return "index";
	}
	
	@GetMapping("/{ticker_name}")
	public String index1(Model model, @PathVariable("ticker_name")String ticker_name) {
		
		model.addAttribute(ticker_name);
		
		return "index";
	}
}
