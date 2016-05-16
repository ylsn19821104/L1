package com.l1.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.l1.dao.RentDtlDao;
import com.l1.entity.Inventory;
import com.l1.entity.RentDtl;
import com.l1.service.InventoryService;
import com.l1.service.SeqService;
import com.l1.util.StorageConstant;
import org.springframework.stereotype.Service;

import com.l1.dao.RentDao;
import com.l1.entity.Rent;
import com.l1.service.RentService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("rentService")
public class RentServiceImpl implements RentService {
    @Resource
    private RentDao rentDao;

    @Resource
    private RentDtlDao rentDtlDao;

    @Resource
    private SeqService seqService;

    @Resource
    private InventoryService inventoryService;

    @Override
    public List<Rent> find(Map<String, Object> map) {
        return rentDao.find(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return rentDao.getTotal(map);
    }

    @Override
    public Integer add(Rent rent) {
        return rentDao.add(rent);
    }

    @Override
    public Integer update(Rent rent) {
        return rentDao.update(rent);
    }


    @Override
    public Integer deleteById(Integer id) {
        return rentDao.deleteById(id);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Integer delete(Integer[] ids) {
        rentDtlDao.deleteByRentIds(ids);
        return rentDao.delete(ids);
    }


    @Override
    public List<Rent> findByIds(String ids) {
        return rentDao.findByIds(ids);
    }

    @Override
    public List<String> findNamesByIds(String ids) {
        return rentDao.findNamesByIds(ids);
    }

    @Override
    public Rent findById(Integer id) {
        return rentDao.findById(id);
    }

    @Override
    public int save(Rent rent) {
        int ret = rentDao.save(rent);
        return ret > 0 ? rent.getId() : -1;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
    @Override
    public int saveRentWithDetails(Rent rent, List<RentDtl> details) {
        String billNo = seqService.next("CZ");
        rent.setBillNo(billNo);
        int id = this.save(rent);
        if (details != null && details.size() > 0) {
            for (RentDtl item : details) {
                item.setRentId(id);
            }
            rentDtlDao.batchSave(details);
        }
        return id;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int updateWithDetails(Rent rent, List<RentDtl> inserted, List<RentDtl> updated, Integer[] ids) {
        int id = this.update(rent);
        if (inserted != null && inserted.size() > 0) {
            for (RentDtl item : inserted) {
                item.setRentId(rent.getId());
            }
            rentDtlDao.batchSave(inserted);
        }
        if (updated != null && updated.size() > 0) {
            for (RentDtl item : updated) {
                item.setRentId(rent.getId());
                rentDtlDao.update(item);
            }
        }
        if (ids != null && ids.length > 0) {
            rentDtlDao.delete(ids);
        }
        return id;
    }

    @Override
    public int unfinish(Integer[] ids, Integer[] warehouseIds) {
        int count = rentDao.unfinish(ids);
        if (count > 0) {
            int inventoryCount = changeInventory(ids, warehouseIds, StorageConstant.UNFINISH);
            if (inventoryCount < 1) {
                throw new RuntimeException("更新库存失败!");
            }
        }else {
            throw new RuntimeException("反审核单据失败!");
        }

        return count;
    }

    @Override
    public int finish(Integer[] ids, Integer[] warehouseIds) {
        int count = rentDao.finish(ids);
        if (count > 0) {
            int inventoryCount = changeInventory(ids, warehouseIds, StorageConstant.FINISH);
            if (inventoryCount < 1) {
                throw new RuntimeException("更新库存失败!");
            }
        } else {
            throw new RuntimeException("审核单据失败!");
        }
        return count;
    }

    private int changeInventory(Integer[] ids, Integer[] warehouseIds, int finishType) {
        int count = 0;
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            int warehouseId = warehouseIds[i];

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rentId", id);
            List<RentDtl> dtls = rentDtlDao.find(map);
            for (RentDtl rentDtl : dtls) {
                count += inventoryService.addAmount(warehouseId, rentDtl.getSkuId(), -rentDtl.getItemAmount(), finishType);
            }
        }
        return count;
    }

    @Override
    public List<Map<Integer, String>> findListFinishedForCombo() {
        return rentDao.findListFinishedForCombo();
    }

}
