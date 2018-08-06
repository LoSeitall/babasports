package com.oracle.sport.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.sport.mapper.ColorMapper;
import com.oracle.sport.mapper.SkuMapper;
import com.oracle.sport.po.Sku;
import com.oracle.sport.service.SkuService;

@Service("skuService")
public class SkuServiceImpl implements SkuService{

	@Autowired
	private SkuMapper skuMapper;
	
	@Autowired
	private ColorMapper colorMapper;
	
	//本方法用于查询指定商品的所有库存信息
	public List<Sku> selectSkus(Long productid) {
		List<Sku> skulist = skuMapper.selectSkus(productid);
		for (Sku sku : skulist) {
			String cname = colorMapper.selectByPrimaryKey(sku.getColorId()).getName();
			sku.setColorName(cname);
		}
		return skulist;
	}

	//本方法用于更新商品库存信息
	public void updateSku(Sku sku) {
		skuMapper.updateByPrimaryKeySelective(sku);
	}
	
	//本方法用于通过主键查询库存信息
	public Sku selectSkuByPrimaryKey(Long sid) {
		Sku sku = skuMapper.selectByPrimaryKey(sid);
		return sku;
	}
	
}
