package com.l1.dao;

import com.l1.entity.StockAdjustDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface StockAdjustDtlDao {

    /**
     * 新增明细
     * @param dtl
     * @return
     */
    Integer add(StockAdjustDtl dtl);

    /**
     * 批量保存明细
     * @param dtls
     * @return
     */
    int batchSave(List<StockAdjustDtl> dtls);

    Integer update(StockAdjustDtl dtl);

    /**
     * 模糊查询
     * @param map
     * @return
     */
    List<StockAdjustDtl> find(Map<String, Object> map);

    /**
     * 根据明细ID数组获得明细对象集合
     * @param dtlIds
     * @return
     */
    List<StockAdjustDtl> findByDtlIds(Integer[] dtlIds);

    /**
     * 根据主表ID获得明细对象集合
     * @param id
     * @return
     */
    List<StockAdjustDtl> findById(Integer id);

    /**
     *根据明细ID获得明细对象
     */
    StockAdjustDtl findByDtlId(Integer dtlId);

    /**
     * 获得调整明细数
     * @param id
     * @return
     */
    Long getTotalByKctzId(Integer id);

    /**
     * 删除指定明细ID
     * @param id
     * @return
     */
    Integer deleteByDtlId(Integer id);

    /**
     * 根据明细DtlID删除明细
     * @param ids
     */
    void deleteByDtlIds(Integer[] ids);

    /**
     * 根据主表ID删除库存调整明细
     * @param KctzId
     */
    void deleteByKctzId(Integer KctzId);

    /**
     * 模糊查询明细数目
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

}
