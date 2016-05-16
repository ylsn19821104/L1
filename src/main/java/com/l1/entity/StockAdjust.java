package com.l1.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StockAdjust {
    private Integer id;
    private String billNo;

    private Integer warehouseId;
    private String warehouseName;

    private int stat;
    private String statName;

    private Integer create_by;
    private String create_byName;

    private Integer update_by;
    private String update_byName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public Integer getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    public String getCreate_byName() {
        return create_byName;
    }

    public void setCreate_byName(String create_byName) {
        this.create_byName = create_byName;
    }

    public Integer getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Integer update_by) {
        this.update_by = update_by;
    }

    public String getUpdate_byName() {
        return update_byName;
    }

    public void setUpdate_byName(String update_byName) {
        this.update_byName = update_byName;
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
