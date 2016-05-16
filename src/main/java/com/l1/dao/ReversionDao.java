package com.l1.dao;

import com.l1.entity.Reversion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
@Repository
public interface ReversionDao {
    List<Map<Integer,String>> loadAvailableRentBillNos();

    Reversion loadReversionFromRent(Integer rentId);

    List<Reversion> find(Map<String, Object> map);

    int remove(Integer[] ids);

    List<Reversion> loadReversionDtlsFromRentDtl(Integer rentId);

    int save(Reversion reversion);

    int finish(Integer[] ids);

    int unfinish(Integer[] ids);
}
