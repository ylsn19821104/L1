package com.l1.entity;

import java.util.Date;

public class StockIn {
    private Integer id;
    private String billNo;
    private Integer billStat;
    private String billStatName;
    private Integer stockOutBillId;
    private String stockOutBillNo;

    private Integer stockOutWarehouseId;
    private String stockOutWarehouseName;
    private Integer stockInWarehouseId;
    private String stockInWarehouseName;

    private Date billDate;
    private Integer totalStockIn;

    private Integer create_by;
    private Integer update_by;

    private Date create_time;
    private Date update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Integer getBillStat() {
        return billStat;
    }

    public void setBillStat(Integer billStat) {
        this.billStat = billStat;
    }

    public String getBillStatName() {
        return billStatName;
    }

    public void setBillStatName(String billStatName) {
        this.billStatName = billStatName;
    }

    public Integer getStockOutBillId() {
        return stockOutBillId;
    }

    public void setStockOutBillId(Integer stockOutBillId) {
        this.stockOutBillId = stockOutBillId;
    }

    public String getStockOutBillNo() {
        return stockOutBillNo;
    }

    public void setStockOutBillNo(String stockOutBillNo) {
        this.stockOutBillNo = stockOutBillNo;
    }

    public Integer getStockOutWarehouseId() {
        return stockOutWarehouseId;
    }

    public void setStockOutWarehouseId(Integer stockOutWarehouseId) {
        this.stockOutWarehouseId = stockOutWarehouseId;
    }

    public String getStockOutWarehouseName() {
        return stockOutWarehouseName;
    }

    public void setStockOutWarehouseName(String stockOutWarehouseName) {
        this.stockOutWarehouseName = stockOutWarehouseName;
    }

    public Integer getStockInWarehouseId() {
        return stockInWarehouseId;
    }

    public void setStockInWarehouseId(Integer stockInWarehouseId) {
        this.stockInWarehouseId = stockInWarehouseId;
    }

    public String getStockInWarehouseName() {
        return stockInWarehouseName;
    }

    public void setStockInWarehouseName(String stockInWarehouseName) {
        this.stockInWarehouseName = stockInWarehouseName;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Integer getTotalStockIn() {
        return totalStockIn;
    }

    public void setTotalStockIn(Integer totalStockIn) {
        this.totalStockIn = totalStockIn;
    }

    public Integer getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    public Integer getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Integer update_by) {
        this.update_by = update_by;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
