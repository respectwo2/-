package com.example.jinyengandothers.dto;

import java.util.List;

import lombok.Data;

@Data
public class BacktestVariableDto {
	
	private List<String> coins;
	private List<Double> ratios;
	private List<String> strategies;
}
