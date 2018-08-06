package com.oracle.sport.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oracle.sport.po.Brand;
import com.oracle.sport.util.PageUtil;

@Component
public interface BrandService {
	
	public Brand selectBrandById(long id);

    PageUtil selectAll(String pageNo, String size, String requestname);

    Brand selectByPrimaryKey(String id);

    boolean deleteByPrimaryKey(String id);

    void updateByPrimaryKey(Brand brand);

    void insertBrand(Brand brand);

    PageUtil selectByCondition(Brand brand,String pageNo, String size, String requestname);
    
    List<Brand> selectBrands();
    
    PageUtil muliDeleteBrand(String ids);
	
}
