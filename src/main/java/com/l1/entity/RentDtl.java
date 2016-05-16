package com.l1.entity;

import java.math.BigDecimal;

public class RentDtl {
	private Integer rentId;
	private Integer id;

	private Integer skuId;
	private String colorName;
	private String sizeDtlName;
	private String itemName;
	private BigDecimal itemPrice;
	private Integer itemAmount;
	private BigDecimal itemRent;
	private BigDecimal itemRepo;

	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeDtlName() {
		return sizeDtlName;
	}

	public void setSizeDtlName(String sizeDtlName) {
		this.sizeDtlName = sizeDtlName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(Integer itemAmount) {
		this.itemAmount = itemAmount;
	}

	public BigDecimal getItemRent() {
		return itemRent;
	}

	public void setItemRent(BigDecimal itemRent) {
		this.itemRent = itemRent;
	}

	public BigDecimal getItemRepo() {
		return itemRepo;
	}

	public void setItemRepo(BigDecimal itemRepo) {
		this.itemRepo = itemRepo;
	}
}
