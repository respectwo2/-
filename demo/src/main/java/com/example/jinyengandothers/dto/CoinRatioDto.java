package com.example.jinyengandothers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinRatioDto {
	
	private String coinName;
	private double ratio;
}