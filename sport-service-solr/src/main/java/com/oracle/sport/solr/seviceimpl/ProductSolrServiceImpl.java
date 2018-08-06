package com.oracle.sport.solr.seviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.sport.po.Product;
import com.oracle.sport.service.ProductSolrService;

import cn.itcast.common.page.Pagination;

@Service("productSolrService")
public class ProductSolrServiceImpl implements ProductSolrService{

	@Autowired
	private HttpSolrServer solrServer;
	
	public Pagination selectProductSolr(String keyword, Integer pageNo, Integer pageSize) {
		
		if(pageNo==null){
			pageNo = 1;
		}
		if(pageSize==null){
			pageSize = 24;
		}
		//拼接条件
		StringBuilder params = new StringBuilder();
		List<Product> products = new ArrayList<Product>();
		SolrQuery solrQuery = new SolrQuery();
		
		//关键词
		solrQuery.set("q","name_ik:"+keyword);
		params.append("keyword=").append(keyword);
		
		//高亮关键字
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("name_ik");
		//样式<span style='color:red'>2016</span>
		solrQuery.setHighlightSimplePre("<span style='color:red'>");
		solrQuery.setHighlightSimplePost("</span>");
		//排序
		solrQuery.addSort("price",ORDER.asc);
		//分页 limit 开始行
		solrQuery.setStart((pageNo-1)*pageSize);
		solrQuery.setRows(pageSize);
		//执行查询
		QueryResponse response = null;
		try {
			response = solrServer.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//取高亮关键词
		Map<String,Map<String,List<String>>> highlighting= response.getHighlighting();
		//Map K:V 442 : Map
		//Map K:V name_ik : List<String>
		//List<String> list 2016xxxxx
		//结果集
		SolrDocumentList docs = response.getResults();
		//发现的条数(总条数) 构建分页用
		long numFound = docs.getNumFound();
		for(SolrDocument doc : docs){
			//构建商品对象
			Product product2 = new Product();
			//商品ID
			String id = (String) doc.get("id");
			product2.setId(Long.parseLong(id));
			System.out.println(id);
			//商品名称 ik
			Map<String,List<String>> map = highlighting.get(id);
			List<String> list = map.get("name_ik");
			product2.setName(list.get(0));
			//图片
			String imgUrl = (String) doc.get("imgUrl");
			product2.setImgUrl(imgUrl.split(",")[0]);
			product2.setImgUrls(imgUrl.split(","));
			//价格 售价
			product2.setPrice((Float)doc.get("price"));
			System.out.println("pro:"+product2);
			products.add(product2);
		
		}
		//构建分页对象
		Pagination pagination = new Pagination(pageNo, pageSize, (int)numFound, products);
		System.out.println(products);
		String url = "/Search";
		pagination.pageView(url, params.toString());
		return pagination;
	}

}
