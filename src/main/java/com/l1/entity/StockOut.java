package com.l1.entity;

import java.util.Date;

public class StockOut {
    private Integer id;
    private String billNo;
    private Integer billStat;
    private String billStatName;


    private Integer StockOutWarehouseId;
    private String StockOutWarehouseName;
    private Integer StockInWarehouseId;
    private String StockInWarehouseName;

    private Date billDate;
    private Integer totalStockOut;


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

    public Integer getStockOutWarehouseId() {
        return StockOutWarehouseId;
    }

    public void setStockOutWarehouseId(Integer stockOutWarehouseId) {
        StockOutWarehouseId = stockOutWarehouseId;
    }

    public String getStockOutWarehouseName() {
        return StockOutWarehouseName;
    }

    public void setStockOutWarehouseName(String stockOutWarehouseName) {
        StockOutWarehouseName = stockOutWarehouseName;
    }

    public Integer getStockInWarehouseId() {
        return StockInWarehouseId;
    }

    public void setStockInWarehouseId(Integer stockInWarehouseId) {
        StockInWarehouseId = stockInWarehouseId;
    }

    public String getStockInWarehouseName() {
        return StockInWarehouseName;
    }

    public void setStockInWarehouseName(String stockInWarehouseName) {
        StockInWarehouseName = stockInWarehouseName;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Integer getTotalStockOut() {
        return totalStockOut;
    }

    public void setTotalStockOut(Integer totalStockOut) {
        this.totalStockOut = totalStockOut;
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
