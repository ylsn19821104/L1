package com.l1.dao;

import java.util.List;
import java.util.Map;

import com.l1.entity.Sku;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuDao {

     List<Sku> find(Map<String, Object> map);

     List<Map<String, String>> findForCombo(Map<String, Object> map);
     List<Map<String, String>> findAllForCombo();
     List<Map<String, String>> findForStockOutDtlCombo(@Param("warehouseId") Integer warehouseId,@Param("stockOutId") Integer stockOutId);


     List<Map<String, String>> findForUploadCombo(Map<String, Object> map);

     List<Sku> findByIds(String ids);

     Sku findById(Integer id);

     Long getTotal(Map<String, Object> map);

     Integer add(Sku sku);

     Integer update(Sku sku);

     Integer deleteById(Integer id);

     Integer deleteByItemId(Integer itemId);

     Integer updateImageId(Integer id, Integer ImageId);

}
