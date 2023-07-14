package com.example.jinyengandothers.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.jinyengandothers.entity.UBAIPrice;
import com.example.jinyengandothers.entity.UBMIPrice;

@Mapper
public interface IndexPriceMapper {

    @Select("SELECT * FROM ubmiprice")
    List<UBMIPrice> getAllUBMIPrices();
    
    @Select("Select * from ubaiPrice")
    List<UBAIPrice> getAllUBAIPrices();
	
}
