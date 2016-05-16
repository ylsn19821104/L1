package com.l1.service;

import com.l1.entity.StockOut;
import com.l1.entity.StockOutDtl;

import java.util.List;
import java.util.Map;

public interface StockOutService {
    List<StockOut> find(Map<String, Object> map);

    List<StockOut> findByIds(String ids);

    StockOut findById(Integer id);

    List<String> findNamesByIds(String ids);

    Long getTotal(Map<String, Object> map);

    Integer add(StockOut StockOut);

    Integer update(StockOut StockOut);

    Integer deleteById(Integer id);

    int save(StockOut stockOut);

    Integer delete(Integer[] ids);

    int saveStockOutWithDetails(StockOut stockOut, List<StockOutDtl> details);


    int updateWithDetails(StockOut stockOut, List<StockOutDtl> inserted, List<StockOutDtl> updated, Integer[] ids);

    int finish(Integer[] ids, Integer[] warhosueIds);

    int unfinish(Integer[] ids, Integer[] warhosueIds);

    List<Map<Integer, String>> findListFinishedForCombo();
}
