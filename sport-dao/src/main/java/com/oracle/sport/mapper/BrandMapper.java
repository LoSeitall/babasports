package com.oracle.sport.mapper;

import java.util.List;

import com.oracle.sport.po.Brand;
import com.oracle.sport.util.LimitNum;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

	List<Brand> selectAll(LimitNum ln);

	List<Brand> selectAllCount();

	List<Brand> selectByCondition(Brand brand);

	List<Brand> selectByConditionCount(Brand brand);
	
	List<Brand> selectBrands();
}