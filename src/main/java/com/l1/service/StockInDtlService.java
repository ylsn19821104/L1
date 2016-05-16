package com.l1.service;

import com.l1.entity.StockInDtl;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
public interface StockInDtlService {
    List<StockInDtl> find(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);

    void add(StockInDtl stockInDtl);

    void update(StockInDtl stockInDtl);

    void delete(String[] ids);

    StockInDtl findById(int id);

    List<StockInDtl> loadStockOutDtlsForStockIn(Integer id);

    List<StockInDtl> findStockInDtlsById(Integer stockInId);
}
