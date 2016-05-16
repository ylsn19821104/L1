package com.l1.service.impl;

import com.l1.dao.StockOutDtlDao;
import com.l1.entity.StockOutDtl;
import com.l1.service.StockOutDtlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("stockOutDtlService")
public class StockOutDtlServiceImpl implements StockOutDtlService {
	@Resource
	private StockOutDtlDao stockOutDtlDao;

	@Override
	public List<StockOutDtl> find(Map<String, Object> map) {
		return stockOutDtlDao.find(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return stockOutDtlDao.getTotal(map);
	}

	@Override
	public Integer add(StockOutDtl stockOutDtl) {
		return stockOutDtlDao.add(stockOutDtl);
	}

	@Override
	public Integer update(StockOutDtl stockOutDtl) {
		return stockOutDtlDao.update(stockOutDtl);
	}

	@Override
	public Integer deleteById(Integer id) {
		return stockOutDtlDao.deleteById(id);
	}

	@Override
	public List<StockOutDtl> findByIds(String ids) {
		return stockOutDtlDao.findByIds(ids);
	}

	@Override
	public List<String> findNamesByIds(String ids) {
		return stockOutDtlDao.findNamesByIds(ids);
	}

	@Override
  public StockOutDtl findById(Integer id) {
		return stockOutDtlDao.findById(id);
	}

  @Override
  public void delete(Integer[] ids) {
      stockOutDtlDao.delete(ids);
  }

}
