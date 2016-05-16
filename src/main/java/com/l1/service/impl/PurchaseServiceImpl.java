package com.l1.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.l1.service.InventoryService;
import com.l1.util.StorageConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.l1.dao.InventoryDao;
import com.l1.dao.PurchaseDao;
import com.l1.dao.PurchaseDtlDao;
import com.l1.dao.SkuDao;
import com.l1.entity.Inventory;
import com.l1.entity.Purchase;
import com.l1.entity.PurchaseDtl;
import com.l1.entity.Sku;
import com.l1.service.PurchaseService;
import com.l1.service.SeqService;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
    @Resource
    private PurchaseDao purchaseDao;

    @Resource
    private PurchaseDtlDao purchaseDtlDao;

    @Resource
    private InventoryService inventoryService;

    @Resource
    private SkuDao skuDao;

    @Resource
    private SeqService seqService;

    @Override
    public List<Purchase> find(Map<String, Object> map) {
        return purchaseDao.find(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return purchaseDao.getTotal(map);
    }

    @Override
    public Integer add(Purchase purchase) {
        return purchaseDao.add(purchase);
    }

    @Override
    public Integer update(Purchase purchase) {
        return purchaseDao.update(purchase);
    }

    @Override
    public Integer deleteById(Integer id) {
        return purchaseDao.deleteById(id);
    }

    @Override
    public Integer delete(Integer[] ids) {
        int count = purchaseDao.delete(ids);

        for (Integer id : ids) {
            purchaseDtlDao.deleteByPuchaseId(id);
        }

        return count;
    }

    @Override
    public List<Purchase> findByIds(String ids) {
        return purchaseDao.findByIds(ids);
    }

    @Override
    public List<String> findNamesByIds(String ids) {
        return purchaseDao.findNamesByIds(ids);
    }

    @Override
    public Purchase findById(Integer id) {
        return purchaseDao.findById(id);
    }

    @Override
    public int save(Purchase purchase) {
        return purchaseDao.save(purchase);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int savePurchaseWithDetails(Purchase purchase, List<PurchaseDtl> dtls) {
        String billNo = seqService.next("CG");
        purchase.setBillNo(billNo);

        int id = this.save(purchase);
        if (dtls != null && dtls.size() > 0) {
            for (PurchaseDtl dtl : dtls) {
                dtl.setId(purchase.getId());
            }
            purchaseDtlDao.batchSave(dtls);
        }

        return id;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int updatePurchaseWithDetails(Purchase purchase, List<PurchaseDtl> insertList, List<PurchaseDtl> updateList,
                                         Integer[] deleted) {
        if (purchase.getBillNo() == null || purchase.getBillNo().trim().length() <= 0) {
            String billNo = seqService.next("CG");
            purchase.setBillNo(billNo);
        }

        int id = this.update(purchase);
        if (insertList.size() > 0) {
            for (PurchaseDtl dtl : insertList) {
                dtl.setId(purchase.getId());
            }

            purchaseDtlDao.batchSave(insertList);
        }

        if (updateList.size() > 0) {
            for (PurchaseDtl dtl : updateList) {
                dtl.setId(purchase.getId());
                purchaseDtlDao.update(dtl);
            }
        }

        if (deleted != null && deleted.length > 0) {
            purchaseDtlDao.deleteByDtlIds(deleted);
        }

        return id;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int finish(Integer[] ids, Integer[] warehouseIds) {
        int count;
        int inventoryCount;
        count = purchaseDao.finish(ids);
        if (count > 0) {
            inventoryCount = changeInventory(ids, warehouseIds, StorageConstant.FINISH);
            if(inventoryCount<1){
                throw new RuntimeException("更新库存失败!");
            }
        }else{
            throw new RuntimeException("审核单据失败!");
        }
        return count;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int unfinish(Integer[] ids, Integer[] warehouseIds) {
        int count = purchaseDao.unfinish(ids);
        int inventoryCount;
        if (count > 0) {
            inventoryCount = changeInventory(ids, warehouseIds, StorageConstant.UNFINISH);
            if(inventoryCount<1){
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
            List<PurchaseDtl> dtls = purchaseDtlDao.findByPurchaseId(id);
            for (PurchaseDtl purchaseDtl : dtls) {
                count+=inventoryService.addAmount(warehouseId, purchaseDtl.getSkuId(), purchaseDtl.getItemAmount(), finishType);
            }
        }
        return count;
    }

}
