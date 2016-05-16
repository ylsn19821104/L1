package com.l1.dao;

import com.l1.entity.Inventory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InventoryDao {
    List<Inventory> find(Map<String, Object> map);

    List<Inventory> findByIds(String[] ids);

    Inventory findById(Integer id);

    List<String> findNamesByIds(String ids);

    Long getTotal(Map<String, Object> map);

    Integer add(Inventory inventory);

    Integer update(Inventory inventory);

    Integer delete(String[] ids);

    Integer deleteById(int id);

    int save(Inventory inventory);

    Inventory getInventory(Integer warehouseId, Integer skuId);

    Inventory getInventoryForUpdate(Integer warehouseId, Integer skuId);
}
