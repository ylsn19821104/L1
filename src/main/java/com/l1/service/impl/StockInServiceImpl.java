package com.l1.service.impl;

import com.l1.dao.StockInDao;
import com.l1.dao.StockInDtlDao;
import com.l1.entity.StockIn;
import com.l1.entity.StockInDtl;
import com.l1.service.InventoryService;
import com.l1.service.StockInService;
import com.l1.service.SeqService;
import com.l1.util.StorageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */

@Repository("stockInService")
public class StockInServiceImpl implements StockInService{
    @Autowired
    private StockInDao stockInDao;
    @Autowired
    private StockInDtlDao stockInDtlDao;
    @Autowired
    private SeqService seqService;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public List<StockIn> find(Map<String, Object> map) {
        return stockInDao.find(map);
    }

    @Override
    public StockIn findById(Integer id) {
        return null;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return null;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int save(StockIn stockIn, List<StockInDtl> details) {
        stockIn.setBillNo(seqService.next("RK"));
        int count = stockInDao.save(stockIn);
        int id = stockIn.getId();
        for(StockInDtl item:details){
            item.setStockInId(id);
        }
        stockInDtlDao.batchSave(details);
        return count;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int delete(Integer[] ids) {
        stockInDtlDao.deleteByStockInIds(ids);
        return stockInDao.remove(ids);
    }

    @Override
    public void update(StockIn stockIn) {

    }

    @Override
    public int update(StockIn stockIn, List<StockInDtl> details) {
        return 0;
    }

    @Override
    public int finish(Integer[] ids,Integer[] warehouseIds) {
        int count = stockInDao.finish(ids);
        if (count > 0) {
            int inventoryCount = changeInventory(ids, warehouseIds, StorageConstant.FINISH);
            if (inventoryCount < 1) {
                throw new RuntimeException("更新库存失败!");
            }
        }else{
            throw new RuntimeException("反审核单据失败!");
        }
        return count;
    }

    @Override
    public int unfinish(Integer[] ids,Integer[] warehouseIds) {
        int count = stockInDao.unfinish(ids);
        if (count > 0) {
            int inventoryCount = changeInventory(ids, warehouseIds, StorageConstant.UNFINISH);
            if (inventoryCount < 1) {
                throw new RuntimeException("更新库存失败!");
            }
        }else{
            throw new RuntimeException("反审核单据失败!");
        }
        return count;
    }

    private int changeInventory(Integer[] ids, Integer[] warehouseIds, int finishType) {
        int count = 0;
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            int warehouseId = warehouseIds[i];
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("stockInId", id);

            List<StockInDtl> dtls = stockInDtlDao.find(params);
            for (StockInDtl stocInDtl : dtls) {
                count += inventoryService.addAmount(warehouseId, stocInDtl.getSkuId(), stocInDtl.getStockInAmount(), finishType);
            }
        }
        return count;
    }

    @Override
    public List<Map<Integer, String>> loadAvailableStockOutBillNos() {
        return stockInDao.loadAvailableStockOutBillNos();
    }

    @Override
    public StockIn loadStockInFromStockOut(Integer stockOutId) {
        return stockInDao.loadStockInFromStockOut(stockOutId);
    }
}
