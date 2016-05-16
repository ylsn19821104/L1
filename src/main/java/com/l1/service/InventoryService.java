package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Inventory;

public interface InventoryService {
    List<Inventory> find(Map<String, Object> map);

    List<Inventory> findByIds(String[] ids);

    Inventory findById(Integer id);

    Long getTotal(Map<String, Object> map);

    Integer add(Inventory inventory);

    Integer update(Inventory inventory);

    Integer deleteById(Integer id);

    void save(Inventory inventory);

    Integer delete(String[] ids);

    Inventory getInventory(Integer warehouseId, Integer skuId);

    Inventory getInventoryForUpdate(Integer warehouseId, Integer skuId);

    /**
     * 增减库存
     */
    int addAmount(Integer warehouseId, Integer skuId, Integer amount, int finishType);

}
