package com.l1.dao;

import com.l1.entity.Rent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RentDao {
    List<Rent> find(Map<String, Object> map);

    List<Rent> findByIds(String ids);

    Rent findById(Integer id);

    List<String> findNamesByIds(String ids);

    Long getTotal(Map<String, Object> map);

    Integer add(Rent color);

    Integer update(Rent color);

    Integer delete(Integer[] ids);

    Integer deleteById(int id);

    int save(Rent rent);

    int finish(Integer[] ids);

    int unfinish(Integer[] ids);

    List<Map<Integer, String>> findListFinishedForCombo();
}
