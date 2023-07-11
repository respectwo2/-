package com.example.jinyengandothers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jinyengandothers.entity.UBAIPrice;
import com.example.jinyengandothers.entity.UBMIPrice;
import com.example.jinyengandothers.mapper.IndexPriceMapper;

@Service
public class IndexServiceImpl implements IndexService {

    private final IndexPriceMapper indexpriceMapper;

    @Autowired
    public IndexServiceImpl(IndexPriceMapper indexpriceMapper) {
        this.indexpriceMapper = indexpriceMapper;
    }

    @Override
    public List<UBMIPrice> getAllUBMIPrices() {
        return indexpriceMapper.getAllUBMIPrices();
    }

    @Override
    public List<UBAIPrice> getAllUBAIPrices() {
        return indexpriceMapper.getAllUBAIPrices();
    }

}
