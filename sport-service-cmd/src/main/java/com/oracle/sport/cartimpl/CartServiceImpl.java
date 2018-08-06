package com.oracle.sport.cartimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.sport.mapper.SkuMapper;
import com.oracle.sport.po.BuyerCart;
import com.oracle.sport.po.BuyerItem;
import com.oracle.sport.service.CartService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service("cartService")
public class CartServiceImpl implements CartService{
	
	@Autowired
	private JedisPool jedisPool;
	
	public void addBuyerCartToRedis(Long sid, Integer count, String username) {
		Jedis jedis = jedisPool.getResource();
		Map<String,String> map = jedis.hgetAll(username);
		Set<String> keys = map.keySet();
		boolean b = false;
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			if(sid.equals(it.next())){
				b = true;
			}
		}
		if(b){
			jedis.hincrBy(username, String.valueOf(sid), count);
		}else{
			jedis.hsetnx(username, String.valueOf(sid), String.valueOf(count));
		}
	}

	@Autowired
	private SkuMapper skuMapper;
	
	public BuyerCart getBuyerCartFromRedis(String username) {
		Jedis jedis = jedisPool.getResource();
		Map<String,String> map = jedis.hgetAll(username);
		BuyerCart bc = new BuyerCart();
		List<BuyerItem> bilist = new ArrayList<>();
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			Long sid = Long.parseLong(it.next());
			BuyerItem bi = new BuyerItem();
			bi.setAmount(Integer.parseInt(map.get(String.valueOf(sid))));
			bi.setIsHave(true);
			bi.setSku(skuMapper.selectByPrimaryKey(sid));
			bilist.add(bi);
		}
		bc.setItems(bilist);
		return bc;
	}

}
