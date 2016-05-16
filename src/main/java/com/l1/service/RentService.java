package com.l1.service;

import java.util.List;
import java.util.Map;

import com.l1.entity.Rent;
import com.l1.entity.RentDtl;

public interface RentService {
    List<Rent> find(Map<String, Object> map);

    List<Rent> findByIds(String ids);

    Rent findById(Integer id);

    List<String> findNamesByIds(String ids);

    Long getTotal(Map<String, Object> map);

    Integer add(Rent Rent);

    Integer update(Rent Rent);

    Integer deleteById(Integer id);

    int save(Rent rent);

    Integer delete(Integer[] ids);

    int saveRentWithDetails(Rent rent, List<RentDtl> details);


    int updateWithDetails(Rent rent, List<RentDtl> inserted,List<RentDtl> updated,Integer[] ids);

    int finish(Integer[] ids, Integer[] warhosueIds);

    List<Map<Integer,String>> findListFinishedForCombo();

    int unfinish(Integer[] ids, Integer[] warhosueIds);
}
