package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.l1.dao.StockAdjustDtlDao;
import com.l1.entity.StockAdjustDtl;
import com.l1.service.StockAdjustDtlService;

@Service("kctzDtlService")
public class StockAdjustDtlServiceImpl implements StockAdjustDtlService {
    @Resource
    private StockAdjustDtlDao stockAdjustDtlDao;

    @Override
    public Integer add(StockAdjustDtl dtl) {
        return stockAdjustDtlDao.add(dtl);
    }

    @Override
    public int batchSave(List<StockAdjustDtl> dtls) {
        return stockAdjustDtlDao.batchSave(dtls);
    }

    @Override
    public Integer update(StockAdjustDtl dtl) {
        return stockAdjustDtlDao.update(dtl);
    }

    @Override
    public List<StockAdjustDtl> find(Map<String, Object> map) {
        return stockAdjustDtlDao.find(map);
    }

    @Override
    public List<StockAdjustDtl> findByDtlIds(Integer[] dtlIds) {
        return stockAdjustDtlDao.findByDtlIds(dtlIds);
    }

    @Override
    public List<StockAdjustDtl> findById(Integer id) {
        return stockAdjustDtlDao.findById(id);
    }

    @Override
    public StockAdjustDtl findByDtlId(Integer dtlId) {
        return stockAdjustDtlDao.findByDtlId(dtlId);
    }

    @Override
    public Long getTotalByKctzId(Integer id) {
        return stockAdjustDtlDao.getTotalByKctzId(id);
    }

    @Override
    public Integer deleteByDtlId(Integer id) {
        return stockAdjustDtlDao.deleteByDtlId(id);
    }

    @Override
    public void deleteByDtlIds(Integer[] ids) {

    }

    @Override
    public void deleteByKctzId(Integer KctzId) {

    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return null;
    }
}
