package com.example.jinyengandothers.dto;

import java.time.ZonedDateTime;
import java.util.Date;
import org.ta4j.core.num.Num;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkerDto {
	
	private long tradeDate;
	private long price;
	
	public MarkerDto(ZonedDateTime tradeDate, long price){
		this.tradeDate = tradeDate.toEpochSecond();
		this.price = price;
	}
}
