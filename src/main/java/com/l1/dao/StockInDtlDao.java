package com.l1.dao;

import com.l1.entity.StockInDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
@Repository
public interface StockInDtlDao {
    List<StockInDtl> loadStockInDtlFromStockOutDtl(Integer id);

    int batchSave(List<StockInDtl> details);

    List<StockInDtl> findStockInDtlsById(Integer id);

    int deleteByStockInIds(Integer[] ids);

    List<StockInDtl> find(Map<String, Object> map);
}
