package com.l1.service.impl;

import com.l1.dao.StockInDtlDao;
import com.l1.entity.StockInDtl;
import com.l1.service.StockInDtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
@Service("stockInDtlService")
public class StockInDtlServiceImpl implements StockInDtlService{

    @Autowired
    private StockInDtlDao stockInDtlDao;

    @Override
    public List<StockInDtl> find(Map<String, Object> map) {
        return null;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return null;
    }

    @Override
    public void add(StockInDtl stockInDtl) {

    }

    @Override
    public void update(StockInDtl stockInDtl) {

    }

    @Override
    public void delete(String[] ids) {

    }

    @Override
    public StockInDtl findById(int id) {
        return null;
    }

    @Override
    public List<StockInDtl> loadStockOutDtlsForStockIn(Integer id) {
        return stockInDtlDao.loadStockInDtlFromStockOutDtl(id);
    }

    @Override
    public List<StockInDtl> findStockInDtlsById(Integer id) {
        return stockInDtlDao.findStockInDtlsById(id);
    }
}
