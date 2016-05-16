package com.l1.dao;

import com.l1.entity.ReversionDtl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luopotaotao on 2016/5/13.
 */
@Repository
public interface ReversionDtlDao {
    List<ReversionDtl> loadReversionDtlFromRentDtl(Integer id);

    int batchSave(List<ReversionDtl> details);

    List<ReversionDtl> findRentDtlsById(Integer id);

    int deleteByReversionIds(Integer[] ids);
}
