package com.oracle.sport.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oracle.sport.po.Color;

@Component
public interface ColorService {
	
	Color selectColorById(Long id);
	
	List<Color> selectAllColor();
}
