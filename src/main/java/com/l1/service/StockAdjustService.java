package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.StockAdjust;
import com.l1.entity.StockAdjustDtl;

public interface StockAdjustService {
    public Integer add(StockAdjust stockAdjust);

    public Integer update(StockAdjust stockAdjust);

    public List<StockAdjust> find(Map<String, Object> map);

    public List<StockAdjust> findByIds(String ids);

    public StockAdjust findById(Integer id);

    public Long getTotal(Map<String, Object> map);

    public Integer delete(Integer[] ids);

    public Integer deleteById(int id);

    int finish(Integer[] ids, Integer[] warehouseIds);

    int unfinish(Integer[] ids, Integer[] warehouseIds);

    int updateKctzWithDetails(StockAdjust StockAdjust, List<StockAdjustDtl> insertList, List<StockAdjustDtl> updateList,
                              Integer[] deleted);

    int saveKctzWithDetails(StockAdjust StockAdjust, List<StockAdjustDtl> insertList);
}
