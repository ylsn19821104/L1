package com.l1.dao;

import com.l1.entity.StockIn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
@Repository
public interface StockInDao {
    List<Map<Integer, String>> loadAvailableStockOutBillNos();

    StockIn loadStockInFromStockOut(Integer stockOutId);

    List<StockIn> find(Map<String, Object> map);

    int remove(Integer[] ids);

    List<StockIn> loadStockInDtlsFromStockOutDtl(Integer stockOutId);

    int save(StockIn stockIn);

    int finish(Integer[] ids);

    int unfinish(Integer[] ids);
}
