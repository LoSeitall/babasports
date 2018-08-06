package com.oracle.sport.po;

import java.io.Serializable;

public class ProductWithBLOBs extends Product implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	private String imgUrl;

    private String description;

    private String packageList;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPackageList() {
        return packageList;
    }

    public void setPackageList(String packageList) {
        this.packageList = packageList == null ? null : packageList.trim();
    }
}