package com.oracle.sport.po;

import java.io.Serializable;
import java.util.Date;

public class Sku implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

    private Long productId;

    private Long colorId;

    private String size;

    private Float marketPrice;

    private Float price;

    private Float deliveFee;

    private Integer stock;

    private Integer upperLimit;

    private Date createTime;
    
    private String colorName;
    
    private Color color;
    
    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public Float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDeliveFee() {
        return deliveFee;
    }

    public void setDeliveFee(Float deliveFee) {
        this.deliveFee = deliveFee;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "Sku [id=" + id + ", productId=" + productId + ", colorId=" + colorId + ", size=" + size
				+ ", marketPrice=" + marketPrice + ", price=" + price + ", deliveFee=" + deliveFee + ", stock=" + stock
				+ ", upperLimit=" + upperLimit + ", createTime=" + createTime + ", colorName=" + colorName + "]";
	}
    
    
}