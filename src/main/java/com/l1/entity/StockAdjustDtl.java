package com.l1.entity;


public class StockAdjustDtl {
	private Integer id;
	private Integer dtlId;

	private Integer skuId;
	private String skuName;

    /**调整前数量,即库存数量*/
    private Integer skuAmount;
    private String colorName;
    private String sizeName;


    /**调整后数量*/
    private Integer tzAmount;

    /**调整数量=调整前数量-调整后数量*/
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDtlId() {
        return dtlId;
    }

    public void setDtlId(Integer dtlId) {
        this.dtlId = dtlId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }



    public Integer getSkuAmount() {
        return skuAmount;
    }

    public void setSkuAmount(Integer skuAmount) {
        this.skuAmount = skuAmount;
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

    public Integer getTzAmount() {
        return tzAmount;
    }

    public void setTzAmount(Integer tzAmount) {
        this.tzAmount = tzAmount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
