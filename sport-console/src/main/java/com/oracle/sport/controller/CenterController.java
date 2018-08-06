package com.oracle.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.sport.service.BrandService;

//用于页面跳转
@Controller
public class CenterController {
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping(value = "test")
	public String test(){
		System.out.println("233333");
		System.out.println(brandService.selectBrandById(1L));
		return null;
	}

    @RequestMapping("toIndex")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("top")
    public String top() {
        return "top";
    }

    @RequestMapping("main")
    public String main() {
        return "main";
    }

    @RequestMapping("left")
    public String left() {
        return "left";
    }

    @RequestMapping("right")
    public String right() {
        return "right";
    }

    @RequestMapping("product_main")
    public String product_main() {
        return "frame/product_main";
    }

    @RequestMapping("product_left")
    public String product_left() {
        return "frame/product_left";
    }

    @RequestMapping("productList")
    public String productList() {
        return "product/list";
    }
}
