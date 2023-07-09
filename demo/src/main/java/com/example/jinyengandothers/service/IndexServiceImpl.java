package com.example.jinyengandothers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jinyengandothers.entity.CoinInfo;
import com.example.jinyengandothers.mapper.CoinInfoMapper;


@Service
public class IndexServiceImpl implements IndexService {

    private final CoinInfoMapper coininfoMapper;

    @Autowired
    public IndexServiceImpl(CoinInfoMapper coininfoMapper) {
        this.coininfoMapper = coininfoMapper;
    }

    @Override
    public CoinInfo getCoinInfoById(int ticker_id) {
        CoinInfo coininfo = coininfoMapper.getCoinInfoById(ticker_id);
        return coininfo;
    }

	@Override
	public CoinInfo getCoinInfoByTicker(String ticker) {
		return coininfoMapper.getCoinInfoByTicker(ticker);
	}

	@Override
	public CoinInfo getCoinInfoByTickerName(String ticker_name) {
		return coininfoMapper.getCoinInfoByTickerName(ticker_name);
	}

	@Override
	public CoinInfo getCoinInfoBySearch(String search) {
		return coininfoMapper.getTickerBySearch(search);
	}
	
	@Override
	public List<String> getAllCoinTicker(){
		return coininfoMapper.getAllTicker();
	}
}






