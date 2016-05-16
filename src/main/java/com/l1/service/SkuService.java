package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Sku;

public interface SkuService {


    List<Sku> find(Map<String, Object> map);

    List<Map<String, String>> findForCombo(Map<String, Object> map);
    List<Map<String, String>> findForStockOutDtlCombo(Integer warehouseId,Integer stockOutId);

    List<Map<String, String>> findAllForCombo();

    List<Sku> findByIds(String ids);

    Sku findById(Integer id);

    Long getTotal(Map<String, Object> map);

    Integer add(Sku sku);

    Integer update(Sku sku);

    Integer deleteById(Integer id);

    Integer deleteByItemId(Integer itemId);


}
