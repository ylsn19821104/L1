package com.l1.service;

import com.l1.entity.StockOutDtl;

import java.util.List;
import java.util.Map;

public interface StockOutDtlService {
    List<StockOutDtl> find(Map<String, Object> map);

    List<StockOutDtl> findByIds(String ids);

    StockOutDtl findById(Integer id);

    List<String> findNamesByIds(String ids);

    Long getTotal(Map<String, Object> map);

    Integer add(StockOutDtl StockOut);

    Integer update(StockOutDtl StockOut);

    Integer deleteById(Integer id);

    void delete(Integer[] ids);
}
