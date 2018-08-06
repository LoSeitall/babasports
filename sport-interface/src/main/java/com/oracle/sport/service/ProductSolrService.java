package com.oracle.sport.service;

import org.springframework.stereotype.Component;

import cn.itcast.common.page.Pagination;

@Component
public interface ProductSolrService {
	Pagination selectProductSolr(String keyword,Integer pageNo,Integer pageSize);
}
