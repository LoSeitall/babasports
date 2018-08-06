package com.oracle.sport.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oracle.sport.po.Product;

import cn.itcast.common.page.Pagination;

@Component
public interface ProductService {
	  
	Pagination selectProducts(Product product);
	    
    int selectProductcount(Product product);
    
    void deleteProducts(Long[] ids);
    
    void deleteProduct(Long id);
   
    void undercarriageProducts(Long[] ids);
    
    void groundingProducts(Long[] ids);
    
    void insertProducts(Product product);
    
    Product selectProductByPrimaryKey(Long id);
    
    void updateProduct(Product product);
    
}
