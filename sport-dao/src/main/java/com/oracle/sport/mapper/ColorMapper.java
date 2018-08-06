package com.oracle.sport.mapper;

import java.util.List;

import com.oracle.sport.po.Color;

public interface ColorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Color record);

    int insertSelective(Color record);

    Color selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Color record);

    int updateByPrimaryKey(Color record);
    
    List<Color> selectAllColor();
}