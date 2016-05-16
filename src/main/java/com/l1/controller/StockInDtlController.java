package com.l1.controller;

import com.l1.entity.PageBean;
import com.l1.entity.StockInDtl;
import com.l1.service.BillStatService;
import com.l1.service.StockInDtlService;
import com.l1.service.SkuService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: hongxp
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/stockInDtl")
public class StockInDtlController {
    @Resource
    private StockInDtlService stockInDtlService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, StockInDtl s_stockInDtl,
                                    HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", s_stockInDtl.getId());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<StockInDtl> stockInDtlList = stockInDtlService.find(map);
        Long total = stockInDtlService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", stockInDtlList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/loadStockOutDtlsForStockIn",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> loadStockOutDtlsForStockIn(@RequestParam  Integer stockOutId) throws Exception {
        List<StockInDtl> list = stockInDtlService.loadStockOutDtlsForStockIn(stockOutId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", list);
        result.put("total", list.size());
        return result;
    }
    @RequestMapping(value = "/findStockInDtlsById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findStockOutDtlsById(Integer stockInId){
        Map<String, Object> result = new HashMap<String, Object>();
        List<StockInDtl> list = stockInDtlService.findStockInDtlsById(stockInId);
        result.put("rows", list);
        result.put("total", list.size());
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(StockInDtl stockInDtl) {

        stockInDtlService.update(stockInDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "ids[]") String[] ids) {
        stockInDtlService.delete(ids);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public StockInDtl findById(@RequestParam("id") int id) {
        StockInDtl stockInDtl = stockInDtlService.findById(id);
        return stockInDtl;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(StockInDtl stockInDtl, @RequestParam("id") int id) throws Exception {
        stockInDtl.setId(id);

//        int resultTotal = 0; // 操作的记录条数
//        if (stockInDtl.getDtlId() == null) {
//            resultTotal = stockInDtlService.add(stockInDtl);
//        } else {
//            resultTotal = stockInDtlService.update(stockInDtl);
//        }

        Map<String, Object> result = new HashMap<String, Object>();
//        if (resultTotal > 0) {
//            result.put("success", true);
//        } else {
//            result.put("success", false);
//        }

        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            stockInDtlService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

}
