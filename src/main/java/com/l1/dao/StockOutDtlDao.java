package com.l1.dao;

import com.l1.entity.StockOutDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StockOutDtlDao {
    List<StockOutDtl> find(Map<String, Object> map);

    List<StockOutDtl> findByIds(String ids);

    StockOutDtl findById(Integer id);

    List<String> findNamesByIds(String ids);

    Long getTotal(Map<String, Object> map);

    Integer add(StockOutDtl color);

    Integer update(StockOutDtl color);

    Integer deleteById(Integer id);

    Integer deleteByStockOutId(Integer id);

    void delete(Integer[] ids);

    int batchSave(List<StockOutDtl> details);

    int deleteByStockOutIds(Integer[] ids);
}
