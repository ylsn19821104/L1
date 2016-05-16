package com.l1.dao;

import com.l1.entity.StockAdjust;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StockAdjustDao {
    public Integer add(StockAdjust stockAdjust);

    public Integer update(StockAdjust stockAdjust);

    public List<StockAdjust> find(Map<String, Object> map);

    public List<StockAdjust> findByIds(String ids);

    public StockAdjust findById(Integer id);

    public Long getTotal(Map<String, Object> map);

    public Integer delete(Integer[] ids);

    public Integer deleteById(int id);

    int finish(Integer[] ids);

    int unfinish(Integer[] ids);
}
