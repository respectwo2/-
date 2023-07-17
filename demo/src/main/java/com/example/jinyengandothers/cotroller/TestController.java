package com.example.jinyengandothers.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
//	http://localhost:8080/
//	https://localhost:8080/main?tvwidgetsymbol=UPBIT%3ASTXKRW
	@GetMapping("/mains")
	public String redirectTest(@RequestParam("tvwidgetsymbol") String tvs) {
		String tickername = tvs.substring(6, 9);
		System.out.println(tickername);
		return "redirect:/main?tvwidgetsymbol="+tickername;
	}
}
