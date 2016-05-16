package com.l1.dao;

import com.l1.entity.StockOut;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StockOutDao {
    List<StockOut> find(Map<String, Object> map);

    List<StockOut> findByIds(String ids);

    StockOut findById(Integer id);

    List<String> findNamesByIds(String ids);

    Long getTotal(Map<String, Object> map);

    Integer add(StockOut color);

    Integer update(StockOut color);

    Integer delete(Integer[] ids);

    Integer deleteById(int id);

    int save(StockOut rent);

    int finish(Integer[] ids);

    int unfinish(Integer[] ids);


    List<Map<Integer, String>> findListFinishedForCombo();
}
