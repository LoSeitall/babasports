package com.oracle.sport.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.sport.po.Brand;
import com.oracle.sport.po.Color;
import com.oracle.sport.po.Product;
import com.oracle.sport.po.Sku;
import com.oracle.sport.service.BrandService;
import com.oracle.sport.service.ColorService;
import com.oracle.sport.service.ProductService;
import com.oracle.sport.service.SkuService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private SkuService skuService;
	
	@Autowired
	private ColorService colorService;
	
	//查询商品信息
	@RequestMapping(value = "productList")
	public String productList(Model model,Product product){
		System.out.println("233:"+product);
		Pagination pagination = productService.selectProducts(product);
		List<Brand> blist = brandService.selectBrands();
		model.addAttribute("blist", blist);
		model.addAttribute("pu", pagination);
		model.addAttribute("product",product);
		return "product/list";
	}
	
	//批量删除商品信息
	@RequestMapping(value = "deleteProducts")
	public String deleteProducts(Long[] ids,Product product)throws Exception{
		//String name,String isShow,Long brandId,Integer pageNo,Integer size
		//		System.out.println("ids:"+ids+"name:"+name+"isShow:"+isShow+"brandId:"+brandId);
		productService.deleteProducts(ids);
		return "redirect:productList?name="+URLEncoder.encode(product.getName(), "utf-8")+"&isShow="+URLEncoder.encode(product.getIsShow().toString(), "utf-8")+"&brandId="+product.getBrandId()+"&pageNo="+product.getPageNo()+"&size=8";
	}
	
	//删除商品信息
	@RequestMapping(value = "deleteProduct")
	public String deleteProduct(Long id,String name,String isShow,Long brandId,Integer pageNo,Integer size)throws Exception{
		System.out.println("id:"+id+"name:"+name+"isShow:"+isShow+"brandId:"+brandId);
		productService.deleteProduct(id);
		return "redirect:productList?name="+URLEncoder.encode(name, "utf-8")+"&isShow="+URLEncoder.encode(isShow, "utf-8")+"&brandId="+brandId+"&pageNo="+pageNo+"&size="+size;
	}
	
	//批量下架
	@RequestMapping(value = "undercarriageProducts")
	public String undercarriageProducts(Long[] ids){
		productService.undercarriageProducts(ids);
		return "redirect:productList";
	}
	
	//批量上架
	@RequestMapping(value = "groundingProducts")
	public String groundingProducts(Long[] ids){
		productService.groundingProducts(ids);
		return "redirect:productList";
	}
	
	//查询商品库存信息
	@RequestMapping(value = "skuList")
	public String skuList(Model model,Long id){
		List<Sku> skulist = skuService.selectSkus(id);
		model.addAttribute("skulist", skulist);
		return "sku/list";
	}
	
	//修改商品库存信息
	@RequestMapping(value = "skuUpdate")
	public String skuUpdate(Sku sku){
		System.out.println(sku);
		skuService.updateSku(sku);
		return "redirect:skuList";
	}
	
	//跳转商品添加页
	@RequestMapping(value = "toProductAdd")
	public String toProductAdd(Model model){
		model.addAttribute("brands",brandService.selectBrands());
		model.addAttribute("color",colorService.selectAllColor());
		return "product/add";
	}
	
	//商品添加页多图片上传回显
	@ResponseBody
	@RequestMapping(value = "productUploadimgs")
	public List<String> productUploadimgs(@RequestParam(value = "pics",required = false)MultipartFile[] files,HttpSession session){
		String path = session.getServletContext().getRealPath("upload");
		List<String> imgurllist = new ArrayList<String>(); 
		File upath = new File(path);
		if(!upath.exists()){
			upath.mkdirs();
		}
		for(MultipartFile file:files){
			try {
				file.transferTo(new File(path+File.separator+file.getOriginalFilename()));
				imgurllist.add("http://localhost:8081/upload/"+file.getOriginalFilename());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return imgurllist;
	}
	
	//商品添加页商品描述上传文件
		@ResponseBody
		@RequestMapping(value = "descUpload")
		public List<String> productUploadimgs(MultipartFile file,HttpSession session){
			String path = session.getServletContext().getRealPath("descupload");
			List<String> imgurllist = new ArrayList<String>(); 
			File upath = new File(path);
			if(!upath.exists()){
				upath.mkdirs();
			}
			try {
				file.transferTo(new File(path+File.separator+file.getOriginalFilename()));
				imgurllist.add("http://localhost:8081/descupload/"+file.getOriginalFilename());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return imgurllist;
		}
	
	//商品添加
	@RequestMapping("productAdd")
	public String productAdd(Model model,Product product){
		productService.insertProducts(product);
		return "redirect:productList";
	}
	
	//商品修改页跳转
	@RequestMapping("toUpdateProduct")
	public String toUpdateProduct(Model model,Long id){
		model.addAttribute("product", productService.selectProductByPrimaryKey(id));
		model.addAttribute("brands", brandService.selectBrands());
		model.addAttribute("color", colorService.selectAllColor());
		return "product/update";
	}
	
	//商品修改
	@RequestMapping("updateProduct")
	public String updateProduct(Product product){
		productService.updateProduct(product);
		return "redirect:productList";
	}
	
	//商品查看页跳转
	@RequestMapping("toShowProduct")
	public String toShowProduct(Model model,Long id){
		model.addAttribute("product", productService.selectProductByPrimaryKey(id));
		model.addAttribute("brands", brandService.selectBrands());
		model.addAttribute("color", colorService.selectAllColor());
		return "product/info";
	}

}
