package com.l1.dao;

import com.l1.entity.PurchaseDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PurchaseDtlDao {
  public Integer add(PurchaseDtl purchaseDtl);

  public int batchSave(List<PurchaseDtl> dtls);

  public Integer update(PurchaseDtl purchaseDtl);

  public List<PurchaseDtl> find(Map<String, Object> map);

  public List<PurchaseDtl> findByDtlIds(Integer[] dtlIds);

  public List<PurchaseDtl> findByPurchaseId(Integer id);

  public PurchaseDtl findByDtlId(Integer dtlId);

  public Long getTotalByPurchaseId(Integer id);

  public Integer deleteByDtlId(Integer id);

  public void deleteByDtlIds(Integer[] ids);


  public void deleteByPuchaseId(Integer purchaseId);

}
