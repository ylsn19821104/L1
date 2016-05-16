package com.l1.entity;

import java.math.BigDecimal;

public class StockOutDtl {
	private Integer id;
	private Integer stockOutId;

	private Integer skuId;
	private String skuImageSuffix;
	private String itemName;
	private String colorName;
	private String sizeName;
	private Integer stockOutAmount;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStockOutId() {
		return stockOutId;
	}

	public void setStockOutId(Integer stockOutId) {
		this.stockOutId = stockOutId;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getSkuImageSuffix() {
		return skuImageSuffix;
	}

	public void setSkuImageSuffix(String skuImageSuffix) {
		this.skuImageSuffix = skuImageSuffix;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public Integer getStockOutAmount() {
		return stockOutAmount;
	}

	public void setStockOutAmount(Integer stockOutAmount) {
		this.stockOutAmount = stockOutAmount;
	}
}
