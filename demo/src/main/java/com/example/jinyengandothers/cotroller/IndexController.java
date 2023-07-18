package com.example.jinyengandothers.cotroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.jinyengandothers.entity.UBAIPrice;
import com.example.jinyengandothers.entity.UBMIPrice;

import com.example.jinyengandothers.service.IndexService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class IndexController {
    private final ObjectMapper objectMapper;
    private final IndexService indexService;

    @Autowired
    public IndexController(ObjectMapper objectMapper, IndexService indexService) {
        this.objectMapper = objectMapper;
        this.indexService = indexService;
    }

    @GetMapping("/chartsJS")
    @ResponseBody
    public Map<String, List<Object[]>> showChart() {
        List<UBAIPrice> ubaiPrices = indexService.getAllUBAIPrices();
        List<UBMIPrice> ubmiPrices = indexService.getAllUBMIPrices();

        List<Object[]> ubaiData = new ArrayList<>();
        for (UBAIPrice ubaiPrice : ubaiPrices) {
            String candleDateTime = ubaiPrice.getCandleDateTime().substring(0, ubaiPrice.getCandleDateTime().length() - 15);
            Object[] dataPoint = {
            	candleDateTime,
            	ubaiPrice.getTradePrice()
            };
            ubaiData.add(dataPoint);
        }

        List<Object[]> ubmiData = new ArrayList<>();
        for (UBMIPrice ubmiPrice : ubmiPrices) {
            String candleDateTime = ubmiPrice.getCandleDateTime().substring(0, ubmiPrice.getCandleDateTime().length() - 15);
            Object[] dataPoint = {
            	candleDateTime,
                ubmiPrice.getTradePrice()
            };
            ubmiData.add(dataPoint);
        }

        Map<String, List<Object[]>> dataMap = new HashMap<>();
        dataMap.put("ubaiData", ubaiData);
        dataMap.put("ubmiData", ubmiData);
        return dataMap;
    }
    
	@GetMapping("/charts")
	public String charts(Model model) {
		return "charts";
	}
	
	@GetMapping("/coinindex")
	public String coinindex(Model model) {
		return "coinindex";
	}
	
	
}
    

