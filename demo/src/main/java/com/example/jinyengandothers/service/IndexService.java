package com.example.jinyengandothers.service;

import java.util.List;
import com.example.jinyengandothers.entity.UBAIPrice;
import com.example.jinyengandothers.entity.UBMIPrice;

public interface IndexService {

	public List<UBMIPrice> getAllUBMIPrices();
	
	public List<UBAIPrice> getAllUBAIPrices();
	
}
