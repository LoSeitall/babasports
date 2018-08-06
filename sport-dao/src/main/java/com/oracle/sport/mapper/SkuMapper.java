package com.oracle.sport.mapper;

import java.util.List;

import com.oracle.sport.po.Sku;

public interface SkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sku record);

    int insertSelective(Sku record);

    Sku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKey(Sku record);
    
    List<Sku> selectSkus(Long productid);
    
    float selectMinByProductId(Long productid);
}