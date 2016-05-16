package com.l1.service;

import com.l1.entity.ReversionDtl;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
public interface ReversionDtlService {
    List<ReversionDtl> find(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);

    void add(ReversionDtl reversionDtl);

    void update(ReversionDtl reversionDtl);

    void delete(String[] ids);

    ReversionDtl findById(int id);

    List<ReversionDtl> loadRentDtlsForReversion(Integer id);

    List<ReversionDtl> findRentDtlsById(Integer reversionId);
}
