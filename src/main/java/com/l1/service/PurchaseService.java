package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Purchase;
import com.l1.entity.PurchaseDtl;

 public interface PurchaseService {
   List<Purchase> find(Map<String, Object> map);

   List<Purchase> findByIds(String ids);

   Purchase findById(Integer id);

   List<String> findNamesByIds(String ids);

   Long getTotal(Map<String, Object> map);

   Integer add(Purchase Purchase);

   Integer update(Purchase Purchase);

   Integer deleteById(Integer id);

   int save(Purchase purchase);

   Integer delete(Integer[] ids);
  
   int finish(Integer[] ids, Integer[] warehouseIds);

  int unfinish(Integer[] ids, Integer[] warehouseIds);

   int updatePurchaseWithDetails(Purchase purchase, List<PurchaseDtl> insertList, List<PurchaseDtl> updateList,
                                       Integer[] deleted);

   int savePurchaseWithDetails(Purchase purchase, List<PurchaseDtl> insertList);
  
}
