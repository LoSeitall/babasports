package com.oracle.sport.po;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Product implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	private Long id;

    private Long brandId;

    private String name;

    private Float weight;

    private Boolean isNew;

    private Boolean isHot;

    private Boolean isCommend;

    private Boolean isShow;

    private Boolean isDel;

    private String colors;

    private String sizes;

    private Date createTime;
    
	private String imgUrl;

    private String description;

    private String packageList;
    
    private Integer size;
    
    private Integer fromLine;
    
    private Integer pageNo;
    
    private String[] imgUrls;
    
    private String[] sizess;
    
    private String[] colorss;
    
    private Float price;
    
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String[] getSizess() {
		return sizess;
	}

	public void setSizess(String[] sizess) {
		this.sizess = sizess;
	}

	public String[] getColorss() {
		return colorss;
	}

	public void setColorss(String[] colorss) {
		this.colorss = colorss;
	}

	public String[] getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String[] imgUrls) {
		this.imgUrls = imgUrls;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getFromLine() {
		return fromLine;
	}

	public void setFromLine(Integer fromLine) {
		this.fromLine = fromLine;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPackageList() {
		return packageList;
	}

	public void setPackageList(String packageList) {
		this.packageList = packageList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public Boolean getIsCommend() {
        return isCommend;
    }

    public void setIsCommend(Boolean isCommend) {
        this.isCommend = isCommend;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors == null ? null : colors.trim();
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes == null ? null : sizes.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", brandId=" + brandId + ", name=" + name + ", weight=" + weight + ", isNew="
				+ isNew + ", isHot=" + isHot + ", isCommend=" + isCommend + ", isShow=" + isShow + ", isDel=" + isDel
				+ ", colors=" + colors + ", sizes=" + sizes + ", createTime=" + createTime + ", imgUrl=" + imgUrl
				+ ", description=" + description + ", packageList=" + packageList + ", size=" + size + ", fromLine="
				+ fromLine + ", pageNo=" + pageNo + ", imgUrls=" + Arrays.toString(imgUrls) + "]";
	}
    
    
}