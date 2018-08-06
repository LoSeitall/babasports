package com.oracle.sport.mapper;

import java.util.List;

import com.oracle.sport.po.Product;
import com.oracle.sport.po.ProductWithBLOBs;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(ProductWithBLOBs record);

    int updateByPrimaryKey(Product record);
    
    List<Product> selectProducts(Product product);
    
    int selectProductcount(Product product);
    
    int deleteProductByIds(Long[] ids);
    
    int groundingProducts(Long[] ids);
    
    int undercarriageProducts(Long[] ids);
}