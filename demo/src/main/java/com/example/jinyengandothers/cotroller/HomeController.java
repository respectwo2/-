package com.example.jinyengandothers.cotroller;

import java.util.LinkedList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.jinyengandothers.dto.CoinWeekChangeDto;
import com.example.jinyengandothers.dao.CoinPriceDao;
import com.example.jinyengandothers.dto.NewsDto;
import com.example.jinyengandothers.service.CryptoCompareNewsService;
import com.example.jinyengandothers.service.CoinInfoService;
import com.example.jinyengandothers.dao.CoinPriceDao;

@Controller
public class HomeController {
	
}
