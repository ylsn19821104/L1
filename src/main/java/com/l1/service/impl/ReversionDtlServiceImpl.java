package com.l1.service.impl;

import com.l1.dao.ReversionDtlDao;
import com.l1.entity.ReversionDtl;
import com.l1.service.ReversionDtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/13.
 */
@Service("reversionDtlService")
public class ReversionDtlServiceImpl implements ReversionDtlService{

    @Autowired
    private ReversionDtlDao reversionDtlDao;

    @Override
    public List<ReversionDtl> find(Map<String, Object> map) {
        return null;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return null;
    }

    @Override
    public void add(ReversionDtl reversionDtl) {

    }

    @Override
    public void update(ReversionDtl reversionDtl) {

    }

    @Override
    public void delete(String[] ids) {

    }

    @Override
    public ReversionDtl findById(int id) {
        return null;
    }

    @Override
    public List<ReversionDtl> loadRentDtlsForReversion(Integer id) {
        return reversionDtlDao.loadReversionDtlFromRentDtl(id);
    }

    @Override
    public List<ReversionDtl> findRentDtlsById(Integer id) {
        return reversionDtlDao.findRentDtlsById(id);
    }
}
