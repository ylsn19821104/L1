package com.l1.entity;

public class StockInDtl {
    private Integer id;
    private Integer stockInId;

    private Integer skuId;
    private String skuImageSuffix;
//    private Integer itemId;
    private String itemName;
    private String stockOutDtlId;
//    private Integer colorId;
    private String colorName;
//    private Integer sizeId;
    private String sizeName;

    private Integer stockOutAmount;
    private Integer stockInAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockInId() {
        return stockInId;
    }

    public void setStockInId(Integer stockInId) {
        this.stockInId = stockInId;
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

    public String getStockOutDtlId() {
        return stockOutDtlId;
    }

    public void setStockOutDtlId(String stockOutDtlId) {
        this.stockOutDtlId = stockOutDtlId;
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

    public Integer getStockInAmount() {
        return stockInAmount;
    }

    public void setStockInAmount(Integer stockInAmount) {
        this.stockInAmount = stockInAmount;
    }
}
