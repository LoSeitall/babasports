package com.oracle.sport.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oracle.sport.po.Sku;

@Component
public interface SkuService {
	
	 List<Sku> selectSkus(Long productid);
	 
	 void updateSku(Sku sku);	 
	 
	 Sku selectSkuByPrimaryKey(Long sid);
}
