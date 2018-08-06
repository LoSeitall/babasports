package com.oracle.sport.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.sport.po.Brand;
import com.oracle.sport.service.BrandService;
import com.oracle.sport.util.PageUtil;


@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
    
    @RequestMapping("brandList")
    public ModelAndView brandList(String pageNo, String size) {
    	ModelAndView mav = new ModelAndView();
        PageUtil pu = brandService.selectAll(pageNo,size,"/brand/brandList?");
        mav.addObject("pu", pu);
        mav.setViewName("brand/list");
        return mav;
    }

    @RequestMapping(value = "toBrandEdit")
    public ModelAndView tobrandEdit(@RequestParam(value = "id") String id) {
        ModelAndView mav = new ModelAndView();
        Brand brand = brandService.selectByPrimaryKey(id);
        mav.addObject("brand",brand);
        mav.setViewName("brand/edit");
        return mav;
    }

    @RequestMapping(value = "brandEdit",method = {RequestMethod.POST})
    public ModelAndView brandEdit(Brand brand,@RequestParam(value = "imgU")MultipartFile file,@RequestParam(value="imgUrl")String imgurl){
        ModelAndView mav = new ModelAndView();
        if(!"".equals(file.getOriginalFilename())){
        	brand.setImgUrl("/brand/"+file.getOriginalFilename());
        }
        brandService.updateByPrimaryKey(brand);
        mav.setViewName("redirect:brandList");
        return mav;
    }

    @RequestMapping(value = "brandDelete")
    public ModelAndView brandDelete(@RequestParam(value = "id") String id,String name,String isDisplay) throws Exception{
    	ModelAndView mav = new ModelAndView();
        boolean flag = brandService.deleteByPrimaryKey(id);
        if(!flag){
            mav.addObject("msg","删除失败!");
        }
        mav.setViewName("redirect:selectByCondition?name="+URLEncoder.encode(name, "utf-8")+"&isDisplay="+URLEncoder.encode(isDisplay, "utf-8"));
        return mav;
    }

    @RequestMapping("toBrandInsert")
    public String toBrandAdd() {
        return "brand/add";
    }

    @RequestMapping(value = "brandInsert")
    public ModelAndView brandInsert(Brand brand,@RequestParam(value = "imgU")MultipartFile file){
        ModelAndView mav = new ModelAndView();
        brand.setImgUrl("/brand/"+file.getOriginalFilename());
        brandService.insertBrand(brand);
        if(brand.getName()==null||brand.getName().equals("")||brand.getImgUrl()==null){
            mav.addObject("msg","必填项未填!!!!!!!");
            mav.setViewName("forward:/toBrandInsert");
        }
        mav.setViewName("redirect:brandList");
        return mav;
    }

    @RequestMapping(value = "uploadBrandImg",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String upload(@RequestParam(value = "imgU",required = false) MultipartFile file, HttpSession session) throws IOException {
        String img_url = "";
        String root_path = session.getServletContext().getRealPath("brand");
        System.out.println(root_path);
        File root = new File(root_path);
        if(!root.exists()){
            root.mkdirs();
        }
        if (!file.isEmpty()) {
            File temp = new File(root_path,file.getOriginalFilename());
            if (!temp.exists()) {
                temp.mkdirs();
            } else {
                temp.delete();
            }
            img_url = "cache"+File.separator;
            file.transferTo(temp);
            System.out.println(temp.getAbsolutePath()+":"+"上传成功!");
        } else {
            return "上传失败!";
        }

        String tempurl = img_url.replaceAll("\\\\","/");
        String[] tus = tempurl.split("/");
        String realimgurl = "";
        for(String s:tus){
            if(s.equals("target")){
                realimgurl += s + "/";
            }
        }

        realimgurl += file.getOriginalFilename();
        return realimgurl;
    }

    @RequestMapping(value = "muliDeleteBrand",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView muliDeleteBrand(@RequestParam("ids") String ids){
        ModelAndView mav = new ModelAndView();
        PageUtil pu = brandService.muliDeleteBrand(ids);
        mav.addObject("pu",pu);
        mav.setViewName("forward:brandList");
        return mav;
    }

    @RequestMapping(value = "selectByCondition",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView selectByCondition(Brand brand,String pageNo,String size,String isDisplay,String name){
    	ModelAndView mav = new ModelAndView();
    	switch (isDisplay){
            case "1":
                brand.setIsDisplay(true);
                break;
            case "0":
                brand.setIsDisplay(false);
                break;
        }
        PageUtil pu = brandService.selectByCondition(brand,pageNo,size,"/brand/selectByCondition?&amp;name="+name+"&amp;isDisplay="+isDisplay+"&");
        mav.addObject("name", name);
        mav.addObject("isDisplay", isDisplay);
        mav.addObject("pu",pu);
        mav.setViewName("brand/list");
        return mav;
    }
}
