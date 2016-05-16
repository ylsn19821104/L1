package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.l1.dao.SkuDao;
import com.l1.entity.Sku;
import com.l1.util.StorageConstant;
import org.springframework.stereotype.Service;

import com.l1.dao.InventoryDao;
import com.l1.entity.Inventory;
import com.l1.service.InventoryService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryDao inventoryDao;

    @Resource
    private SkuDao skuDao;

    @Override
    public List<Inventory> find(Map<String, Object> map) {
        return inventoryDao.find(map);
    }

    @Override
    public List<Inventory> findByIds(String[] ids) {
        return inventoryDao.findByIds(ids);
    }

    @Override
    public Inventory findById(Integer id) {
        return inventoryDao.findById(id);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return inventoryDao.getTotal(map);
    }

    @Override
    public Integer add(Inventory inventory) {
        return inventoryDao.add(inventory);
    }

    @Override
    public Integer update(Inventory inventory) {
        return inventoryDao.update(inventory);
    }

    @Override
    public Integer deleteById(Integer id) {
        return inventoryDao.deleteById(id);
    }

    @Override
    public void save(Inventory inventory) {
        inventoryDao.save(inventory);
    }

    @Override
    public Integer delete(String[] ids) {
        return inventoryDao.delete(ids);
    }

    @Override
    public Inventory getInventory(Integer warehouseId, Integer skuId) {

        return inventoryDao.getInventory(warehouseId, skuId);
    }

    @Override
    public Inventory getInventoryForUpdate(Integer warehouseId, Integer skuId) {
        return inventoryDao.getInventoryForUpdate(warehouseId,skuId);
    }

    @Override
    public int addAmount(Integer warehouseId, Integer skuId, Integer amount, int finishType) {
        int count ;
        Inventory inventory = inventoryDao.getInventoryForUpdate(warehouseId, skuId);
        if (inventory != null) {
            if (inventory.getAmount()==null){
                inventory.setAmount(0);
            }
            if (finishType == StorageConstant.FINISH) {
                inventory.setAmount(inventory.getAmount() + amount);
            } else {
                inventory.setAmount(inventory.getAmount() - amount);
            }

            count = inventoryDao.update(inventory);
        } else {
            inventory = new Inventory();
            inventory.setSkuId(skuId);
            inventory.setWarehouseId(warehouseId);
            inventory.setAmount(amount);

            Sku sku = skuDao.findById(skuId);
            if (sku != null) {
                inventory.setColorId(sku.getColorId());
                inventory.setSizeId(sku.getSizeDtlId());
                inventory.setStyle(sku.getItemName());
            }
            count = inventoryDao.save(inventory);
        }
        return count;
    }

}
