package com.oracle.sport.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.sport.mapper.ColorMapper;
import com.oracle.sport.po.Color;
import com.oracle.sport.service.ColorService;

@Service("colorService")
public class ColorServiceImpl implements ColorService{

	@Autowired
	private ColorMapper colorMapper;
	
	//本方法用于通过主键查询颜色信息
	public Color selectColorById(Long id) {
		Color color = colorMapper.selectByPrimaryKey(id);
		return color;
	}

	//本方法用于查询所有颜色信息
	public List<Color> selectAllColor() {
		return colorMapper.selectAllColor();
	}
		
}
