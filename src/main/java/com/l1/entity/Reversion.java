package com.l1.entity;

import java.math.BigDecimal;
import java.util.Date;
public class Reversion {
    private Integer id;
    private String billNo;
    private Integer billStat;
    private String billStatName;

    private Integer rentBillId;
    private String rentBillNo;
    private Integer rentBillStat;
    private String rentBillStatName;

    private Integer warehouseId;
    private String warehouseName;

    private String customerName;
    private String customerPhone;
    private String customerAddr;
    private String customerCard;

    private Integer supplierId;
    private String supplierName;
    private String expressBillNo;


    private Date beginDate;
    private Date endDate;
    private Date reversionDate;
    private BigDecimal rentMoney;
    private BigDecimal repoMoney;
    private BigDecimal compensateMoney;

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

    public Integer getRentBillId() {
        return rentBillId;
    }

    public void setRentBillId(Integer rentBillId) {
        this.rentBillId = rentBillId;
    }

    public String getRentBillNo() {
        return rentBillNo;
    }

    public void setRentBillNo(String rentBillNo) {
        this.rentBillNo = rentBillNo;
    }

    public Integer getRentBillStat() {
        return rentBillStat;
    }

    public void setRentBillStat(Integer rentBillStat) {
        this.rentBillStat = rentBillStat;
    }

    public String getRentBillStatName() {
        return rentBillStatName;
    }

    public void setRentBillStatName(String rentBillStatName) {
        this.rentBillStatName = rentBillStatName;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddr() {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }

    public String getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(String customerCard) {
        this.customerCard = customerCard;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getExpressBillNo() {
        return expressBillNo;
    }

    public void setExpressBillNo(String expressBillNo) {
        this.expressBillNo = expressBillNo;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getReversionDate() {
        return reversionDate;
    }

    public void setReversionDate(Date reversionDate) {
        this.reversionDate = reversionDate;
    }

    public BigDecimal getRentMoney() {
        return rentMoney;
    }

    public void setRentMoney(BigDecimal rentMoney) {
        this.rentMoney = rentMoney;
    }

    public BigDecimal getRepoMoney() {
        return repoMoney;
    }

    public void setRepoMoney(BigDecimal repoMoney) {
        this.repoMoney = repoMoney;
    }

    public BigDecimal getCompensateMoney() {
        return compensateMoney;
    }

    public void setCompensateMoney(BigDecimal compensateMoney) {
        this.compensateMoney = compensateMoney;
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
