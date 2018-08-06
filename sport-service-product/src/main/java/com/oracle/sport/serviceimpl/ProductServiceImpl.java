package com.oracle.sport.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.sport.mapper.BrandMapper;
import com.oracle.sport.mapper.ColorMapper;
import com.oracle.sport.mapper.ProductMapper;
import com.oracle.sport.mapper.SkuMapper;
import com.oracle.sport.po.Brand;
import com.oracle.sport.po.Color;
import com.oracle.sport.po.Product;
import com.oracle.sport.po.Sku;
import com.oracle.sport.service.ProductService;
import com.oracle.sport.service.SkuService;
import com.oracle.sport.staticpage.StaticPageServiceImpl;
import com.oracle.sport.util.ArrayToString;

import cn.itcast.common.page.Pagination;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private SkuMapper skuMapper;
	
	@Autowired
	private ColorMapper colorMapper;
	
	//查询商品信息(带分页条件查询) Pagination分页工具
	public Pagination selectProducts(Product product) {
		if(product != null){
			if(product.getName()==null&&"".equals(product.getName())){
				product.setName(null);
			}
			if(product.getBrandId()!=null&&product.getBrandId()==0){
				product.setBrandId(null);
			}
			if(product.getSize()==null||product.getSize().equals("")){
				product.setSize(8);
			}
			if(product.getPageNo()==null||product.getPageNo().equals("")){
				product.setPageNo(1);
			}			
			if(product.getIsShow()!=null&&product.getIsShow().equals("")){
				product.setIsShow(null);
			}
		}
		product.setFromLine((product.getPageNo()-1)*product.getSize());
		List<Product> productlist = productMapper.selectProducts(product); 
		for (Product p : productlist) {
			String[] urls = p.getImgUrl().split(",");
			p.setImgUrls(urls);
		}
		System.out.println(productMapper.selectProducts(product).size()+":listsize");
		Pagination pagination = new Pagination(product.getPageNo(),product.getSize(),selectProductcount(product),productlist);
		StringBuilder sb = new StringBuilder();
		sb.append("size=8");
		if(product.getName()!=null){
			sb.append("&name="+product.getName());
		}
		if(product.getBrandId()!=null){
			sb.append("&brandId="+product.getBrandId());
		}
		if(product.getIsShow()!=null){
			sb.append("&isShow="+product.getIsShow());
		}		
		pagination.pageView("/product/productList", sb.toString());
		return pagination;
	}
	
	//本方法用于分页,查询列表Size
	public int selectProductcount(Product product) {
		return productMapper.selectProductcount(product);
	}
	
	//本方法用于批量删除商品信息
	public void deleteProducts(Long[] ids) {
		productMapper.deleteProductByIds(ids);
	}

	//本方法用于通过主键删除一条商品信息
	public void deleteProduct(Long id) {
		productMapper.deleteByPrimaryKey(id);	
	}

	//批量下架
	public void undercarriageProducts(Long[] ids) {
		productMapper.undercarriageProducts(ids);
	}

	//批量上架
	
	@Autowired
	private StaticPageServiceImpl staticPageService;
	
	public void groundingProducts(Long[] ids) {
		productMapper.groundingProducts(ids);
		addSolr(ids);
		for(Long id : ids){			
			Map<String, Object>  root = new HashMap<String, Object>();
			List<Sku> skus = skuMapper.selectSkus(id);
			float minPrice = skuMapper.selectMinByProductId(id);
			Product product = productMapper.selectByPrimaryKey(id);
			product.setPrice(minPrice);
			product.setImgUrls(product.getImgUrl().split(","));
			//遍历一次  相同不要
			Set<Color> colors = new HashSet<Color>();
			for (Sku sku : skus) {
				colors.add(colorMapper.selectByPrimaryKey(sku.getColorId()));
			}
			root.put("product", product);
			root.put("skus", skus);
			root.put("colors", colors);
			staticPageService.productStaticPage(root, String.valueOf(id));
		}
	}

	//添加商品信息
	public void insertProducts(Product product) {
		product.setColors(ArrayToString.changeArray(product.getColorss()));
		product.setSizes(ArrayToString.changeArray(product.getSizess()));
		product.setImgUrl(ArrayToString.changeArray(product.getImgUrls()));
		product.setIsDel(true);
		product.setIsShow(false);
		productMapper.insertSelective(product);
	}

	//通过主键查询商品信息
	public Product selectProductByPrimaryKey(Long id) {
		Product product = productMapper.selectByPrimaryKey(id);
		product.setColorss(product.getColors().split(","));
		product.setImgUrls(product.getImgUrl().split(","));
		product.setSizess(product.getSizes().split(","));
		return product;
	}

	//本方法用于更新商品信息
	public void updateProduct(Product product) {
		product.setColors(ArrayToString.changeArray(product.getColorss()));
		product.setSizes(ArrayToString.changeArray(product.getSizess()));
		if(product.getImgUrls()!=null){
			product.setImgUrl(ArrayToString.changeArray(product.getImgUrls()));
		}
		productMapper.updateByPrimaryKeySelective(product);
	}
	
	//solr添加信息
	@Autowired
	private HttpSolrServer solrServer;
	
	private void addSolr(Long[] ids){
		for(Long id : ids){
			SolrInputDocument sid = new SolrInputDocument();
			sid.addField("id", id);
			Product product = productMapper.selectByPrimaryKey(id);
			sid.addField("brandId",product.getBrandId());
			sid.addField("name_ik", product.getName());
			sid.addField("imgUrl", product.getImgUrl());
			sid.addField("price", skuMapper.selectMinByProductId(id));
			try {
				solrServer.add(sid);
				solrServer.commit();
			} catch (SolrServerException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
