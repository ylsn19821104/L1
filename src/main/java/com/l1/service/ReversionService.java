package com.l1.service;

import com.l1.entity.Reversion;
import com.l1.entity.ReversionDtl;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
public interface ReversionService {
    List<Reversion> find(Map<String, Object> map);

    Reversion findById(Integer id);

    Long getTotal(Map<String, Object> map);

    int save(Reversion reversion, List<ReversionDtl> details);

    int delete(Integer[] ids);

    void update(Reversion reversion);

    int update(Reversion reversion, List<ReversionDtl> details);

    int finish(Integer[] ids, Integer[] warhosueIds);

    List<Map<Integer,String>> loadAvailableRentBillNos();

    Reversion loadReversionFromRent(Integer rentId);

    int unfinish(Integer[] ids, Integer[] warhosueIds);
}
