package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.l1.dao.StockAdjustDtlDao;
import com.l1.entity.StockAdjustDtl;
import com.l1.entity.StockAdjust;
import com.l1.service.InventoryService;
import com.l1.service.SeqService;
import com.l1.util.StorageConstant;
import org.springframework.stereotype.Service;

import com.l1.dao.StockAdjustDao;
import com.l1.service.StockAdjustService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("kctzService")
public class StockAdjustServiceImpl implements StockAdjustService {
    @Resource
    private StockAdjustDao stockAdjustDao;

    @Resource
    private StockAdjustDtlDao stockAdjustDtlDao;

    @Resource
    private SeqService seqService;

    @Resource
    private InventoryService inventoryService;


    @Override
    public List<StockAdjust> find(Map<String, Object> map) {
        return stockAdjustDao.find(map);
    }

    @Override
    public List<StockAdjust> findByIds(String ids) {
        return stockAdjustDao.findByIds(ids);
    }

    @Override
    public StockAdjust findById(Integer id) {
        return stockAdjustDao.findById(id);
    }


    @Override
    public Long getTotal(Map<String, Object> map) {
        return stockAdjustDao.getTotal(map);
    }

    @Override
    public Integer add(StockAdjust stockAdjust) {
        return stockAdjustDao.add(stockAdjust);
    }

    @Override
    public Integer update(StockAdjust stockAdjust) {
        return stockAdjustDao.update(stockAdjust);
    }

    @Override
    public Integer delete(Integer[] ids) {
        return stockAdjustDao.delete(ids);
    }

    @Override
    public Integer deleteById(int id) {
        return stockAdjustDao.deleteById(id);
    }

    @Override
    public int finish(Integer[] ids, Integer[] warehouseIds) {
        int count = stockAdjustDao.finish(ids);
        if (count > 0) {
            int kctzCount = changeInventory(ids, warehouseIds, StorageConstant.FINISH);
            if (kctzCount < 1) {
                throw new RuntimeException("更新库存失败!");
            }
        } else {
            throw new RuntimeException("审核单据失败!");
        }

        return count;
    }

    @Override
    public int unfinish(Integer[] ids, Integer[] warehouseIds) {
        int count = stockAdjustDao.unfinish(ids);
        if (count > 0) {
            int kctzCount = changeInventory(ids, warehouseIds, StorageConstant.UNFINISH);
            if (kctzCount < 1) {
                throw new RuntimeException("更新库存失败!");
            }
        } else {
            throw new RuntimeException("反审核单据失败!");
        }

        return count;
    }

    private int changeInventory(Integer[] ids, Integer[] warehouseIds, int finishType) {
        int count = 0;
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            int warehouseId = warehouseIds[i];
            List<StockAdjustDtl> dtls = stockAdjustDtlDao.findById(id);
            for (StockAdjustDtl stockAdjustDtl : dtls) {
                count += inventoryService.addAmount(warehouseId, stockAdjustDtl.getSkuId(), stockAdjustDtl.getAmount(), finishType);
            }
        }

        return count;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int saveKctzWithDetails(StockAdjust StockAdjust, List<StockAdjustDtl> dtls) {
        String billNo = seqService.next("CG");
        StockAdjust.setBillNo(billNo);

        int id = this.add(StockAdjust);
        if (dtls != null && dtls.size() > 0) {
            for (StockAdjustDtl dtl : dtls) {
                dtl.setId(StockAdjust.getId());
            }
            stockAdjustDtlDao.batchSave(dtls);
        }

        return id;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int updateKctzWithDetails(StockAdjust StockAdjust, List<StockAdjustDtl> insertList, List<StockAdjustDtl> updateList,
                                     Integer[] deleted) {
        if (StockAdjust.getBillNo() == null || StockAdjust.getBillNo().trim().length() <= 0) {
            String billNo = seqService.next("KCTZ");
            StockAdjust.setBillNo(billNo);
        }

        int id = this.update(StockAdjust);
        if (insertList.size() > 0) {
            for (StockAdjustDtl dtl : insertList) {
                dtl.setId(StockAdjust.getId());
            }

            stockAdjustDtlDao.batchSave(insertList);
        }

        if (updateList.size() > 0) {
            for (StockAdjustDtl dtl : updateList) {
                dtl.setId(StockAdjust.getId());
                stockAdjustDtlDao.update(dtl);
            }
        }

        if (deleted != null && deleted.length > 0) {
            stockAdjustDtlDao.deleteByDtlIds(deleted);
        }

        return id;
    }
}
