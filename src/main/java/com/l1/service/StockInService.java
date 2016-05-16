package com.l1.service;

import com.l1.entity.StockIn;
import com.l1.entity.StockInDtl;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
public interface StockInService {
    List<StockIn> find(Map<String, Object> map);

    StockIn findById(Integer id);

    Long getTotal(Map<String, Object> map);

    int save(StockIn reversion, List<StockInDtl> details);

    int delete(Integer[] ids);

    void update(StockIn reversion);

    int update(StockIn reversion, List<StockInDtl> details);

    int finish(Integer[] ids,Integer [] warehouseIds);

    int unfinish(Integer[] ids,Integer [] warehouseIds);

    List<Map<Integer,String>> loadAvailableStockOutBillNos();

    StockIn loadStockInFromStockOut(Integer rentId);
}
