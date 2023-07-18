package com.example.jinyengandothers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jinyengandothers.entity.CoinInfo;
import com.example.jinyengandothers.mapper.CoinInfoMapper;


@Service
public class SearchServiceImpl implements SearchService {

    private final CoinInfoMapper coininfoMapper;

    @Autowired
    public SearchServiceImpl(CoinInfoMapper coininfoMapper) {
        this.coininfoMapper = coininfoMapper;
    }

    @Override
    public CoinInfo getCoinInfoById(int tickerId) {
        CoinInfo coininfo = coininfoMapper.getCoinInfoById(tickerId);
        return coininfo;
    }

	@Override
	public CoinInfo getCoinInfoByTicker(String ticker) {
		return coininfoMapper.getCoinInfoByTicker(ticker);
	}

	@Override
	public CoinInfo getCoinInfoByTickerName(String tickerName) {
		return coininfoMapper.getCoinInfoByTickerName(tickerName);
	}

	@Override
	public CoinInfo getCoinInfoBySearch(String search) {
		return coininfoMapper.getTickerBySearch(search);
	}

	@Override
	public CoinInfo getTickerInfoByTicker(String search) {
		return coininfoMapper.getTickerInfoByTicker(search);
	}

	@Override
	public CoinInfo getTickerIssuerByTicker(String search) {
		return coininfoMapper.getTickerIssuerByTicker(search);
	}
	
	@Override
	public List<String> getAllCoinTicker(){
		return coininfoMapper.getAllTicker();
	}
	
}






