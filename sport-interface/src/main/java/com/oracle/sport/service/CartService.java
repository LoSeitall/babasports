package com.oracle.sport.service;

import org.springframework.stereotype.Component;

import com.oracle.sport.po.BuyerCart;

@Component
public interface CartService {
	
	public void addBuyerCartToRedis(Long sid,Integer count,String username);
	
	public BuyerCart getBuyerCartFromRedis(String username);
}
