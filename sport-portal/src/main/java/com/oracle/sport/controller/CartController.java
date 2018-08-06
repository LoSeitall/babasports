package com.oracle.sport.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.sport.po.BuyerCart;
import com.oracle.sport.po.BuyerItem;
import com.oracle.sport.po.Product;
import com.oracle.sport.po.Sku;
import com.oracle.sport.service.CartService;
import com.oracle.sport.service.ColorService;
import com.oracle.sport.service.ProductService;
import com.oracle.sport.service.SkuService;
import com.oracle.sport.util.RequestUtils;

@Controller
public class CartController {
	
	@Autowired
	private SkuService skuService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ColorService colorService;
	
	//添加购物车
	@RequestMapping("addCart")
	public String addCart(Long sid,Integer buyNum,String url,HttpServletRequest request,HttpServletResponse response){
		//判断用户是否登陆
		//获取cookie
		Cookie[] cs  = request.getCookies();
		String csessionid = RequestUtils.getCSESSIONID(request, response);
		boolean flag = false;
		String username = null;
		for(Cookie cookie : cs){
			if(csessionid.equals(cookie.getName())){
				flag = true;
				username = cookie.getValue();
				break;
			}
		}
		//如何存到redis
		if(flag){
			cartService.addBuyerCartToRedis(sid, buyNum, username);
		}
		//如何存到cookie中
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		if(!flag){
			boolean flag1 = false;
			BuyerCart bc = null;
			for(Cookie cookie : cs){
				if("jdbuyercart".equals(cookie.getName())){
					flag1 = true;
					try {
						bc = om.readValue(URLDecoder.decode(cookie.getValue()), BuyerCart.class);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
			List<BuyerItem> list = null;
			if(flag1){
				//存在购物车追加商品
				list = bc.getItems();
			}else{
				//创建购物车
				list = new ArrayList<BuyerItem>();
			}
			BuyerItem bi = new BuyerItem();
			Sku sku = skuService.selectSkuByPrimaryKey(sid);
			sku.setColor(colorService.selectColorById(sku.getColorId()));
			Product product = productService.selectProductByPrimaryKey(sku.getProductId());
			product.setImgUrls(product.getImgUrl().split(","));
			bi.setAmount(buyNum);
			bi.setIsHave(sku.getStock()>=buyNum);
			bi.setSku(sku);
			bi.setProduct(product);
			boolean b = false;
			for(BuyerItem buyerItem : list){
				if(buyerItem.equals(bi)){
					buyerItem.setAmount(buyNum+buyerItem.getAmount());
					b = true;
					break;
				}
			}
			if(!b){
				list.add(bi);
			}
			//把集合转Json
			StringWriter sw = new StringWriter();
			BuyerCart buyercart = new BuyerCart();
			try {
				buyercart.setItems(list);
				om.writeValue(sw, buyercart);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(sw.toString());
//			String enc = "";
//			try {
//				enc = URLEncoder.encode(sw.toString(), "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			Cookie cookie = new Cookie("jdbuyercart",URLEncoder.encode(sw.toString()));
			cookie.setMaxAge(7*24*60*60);
			response.addCookie(cookie);
		}
		return "redirect:"+url;
	}

	//去购物车
	@RequestMapping("/toCart")
	public String toCart(HttpServletRequest request,HttpServletResponse response,Model model){
		//判断用户是否登陆
		//获取cookie
		Cookie[] cs  = request.getCookies();
		String csessionid = RequestUtils.getCSESSIONID(request, response);
		boolean flag = false;
		String username = null;
		for(Cookie cookie : cs){
			if(csessionid.equals(cookie.getName())){
				flag = true;
				username = cookie.getValue();
				break;
			}
		}
		if(flag){
			model.addAttribute("carts", cartService.getBuyerCartFromRedis(username).getItems());
		}else{
			for(Cookie cookie : cs){
				if("jdbuyercart".equals(cookie.getName())){
					ObjectMapper om = new ObjectMapper();
					om.setSerializationInclusion(Include.NON_NULL);
					try {
						List<BuyerItem> list = om.readValue(URLDecoder.decode(cookie.getValue()), BuyerCart.class).getItems();
						model.addAttribute("carts", om.readValue(URLDecoder.decode(cookie.getValue()), BuyerCart.class).getItems());
						double money = 0;
						for (BuyerItem buyerItem : list) {
							money += buyerItem.getAmount()*buyerItem.getSku().getMarketPrice();
						}
						model.addAttribute("totalmoney", money);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
		return "cart";
	}
}
