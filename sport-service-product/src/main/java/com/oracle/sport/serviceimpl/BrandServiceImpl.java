package com.oracle.sport.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.oracle.sport.mapper.BrandMapper;
import com.oracle.sport.po.Brand;
import com.oracle.sport.service.BrandService;
import com.oracle.sport.util.LimitNum;
import com.oracle.sport.util.PageUtil;

@Service("brandService")
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandMapper brandMapper; 
	
	 //查询所有品牌信息(带分页)
    public PageUtil selectAll(String pageNo, String size, String requestname) {
        if(pageNo == null || pageNo.equals("0")){
            pageNo = "1";
        }
        if(size == null || size.equals("0")){
            size = "8";
        }
        System.out.println(pageNo+":"+size);
    	int pno = Integer.parseInt(pageNo),s = Integer.parseInt(size);
        PageUtil pu = new PageUtil();
        LimitNum ln = new LimitNum((pno-1)*s,s);
        List<Brand> brandlist = brandMapper.selectAll(ln);
        for(Brand brand:brandlist){
            if(brand.getIsDisplay()){
                brand.setStrDis("是");
            }else{
                brand.setStrDis("否");
            }
        }
        pu.setList(brandlist);
        pu.init(pno,brandMapper.selectAllCount().size(),s,requestname);
        return pu;
    }

    //通过主键查询品牌信息
    public Brand selectByPrimaryKey(String id){
        long bid = Long.parseLong(id);
        Brand brand = brandMapper.selectByPrimaryKey(bid);
        return brand;
    }

    //通过主键删除品牌信息
    public boolean deleteByPrimaryKey(String id){
        long bid = Long.parseLong(id);
        int i = brandMapper.deleteByPrimaryKey(bid);
        if(i>=1){
            return true;
        }else{
            return false;
        }
    }

    //通过主键更新品牌信息
    public void updateByPrimaryKey(Brand brand){
    	brandMapper.updateByPrimaryKeySelective(brand);
    }
    
    @Autowired
    private RedisTemplate<String, Object> rt;
    //新增品牌信息
    public void insertBrand(Brand brand){
    	brand.setId(rt.opsForValue().increment("brandid", 1));
    	brandMapper.insertSelective(brand);
    }

    //品牌条件查询
    public PageUtil selectByCondition(Brand brand,String pageNo, String size, String requestname) {
    	 if(pageNo == null || pageNo.equals("0")){
             pageNo = "1";
         }

         if(size == null || size.equals("0")){
             size = "8";
         }
    	int pno = Integer.parseInt(pageNo),s = Integer.parseInt(size);
        brand.setLimitnum((pno-1)*s);
        brand.setSize(s);
        PageUtil pu = new PageUtil();
        List<Brand> brandlist = brandMapper.selectByCondition(brand);
        for(Brand b:brandlist){
            if(b.getIsDisplay()){
                b.setStrDis("是");
            }else{
                b.setStrDis("否");
            }
        }
        pu.setList(brandlist);
        pu.init(pno,brandMapper.selectByConditionCount(brand).size(),s,requestname);
        return pu;
    }
    
    //本方法用于条件查询select下拉菜单中的品牌选项
	public List<Brand> selectBrands() {
		return brandMapper.selectBrands();
	}
	
	//通过主键查询品牌信息
	@Override
	public Brand selectBrandById(long id) {
		Brand brand = brandMapper.selectByPrimaryKey(1L);
		return brand;
	}
	
	//批量删除品牌信息
	@Override
	public PageUtil muliDeleteBrand(String ids) {
		PageUtil pu = selectAll("1", "8", "/brand/brandList");
        String[] temp = ids.split(",");
        for (String id:temp) {
           deleteByPrimaryKey(id);
        }
		return pu;
	}

}
