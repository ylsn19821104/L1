package com.l1.controller;

import com.l1.entity.StockOutDtl;
import com.l1.entity.Sku;
import com.l1.service.BillStatService;
import com.l1.service.StockOutDtlService;
import com.l1.service.SkuService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: luopotaotao
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/stockOutDtl")
public class StockOutDtlController {
    @Resource
    private StockOutDtlService stockOutDtlService;

    @Resource
    private BillStatService billStatService;

    @Resource
    private SkuService skuService;

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
                                    @RequestParam(value = "rows", required = false) String rows, @RequestParam(required = true) String id) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("start", page);
        map.put("size", rows);

        List<StockOutDtl> stockOutDtlList = stockOutDtlService.find(map);
        Long total = stockOutDtlService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", stockOutDtlList);
        result.put("total", total);

        return result;
    }

    @RequestMapping("/findAllById")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "stockOutId", required = false) String stockOutId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stockOutId", stockOutId);

        List<StockOutDtl> linkManList = stockOutDtlService.find(map);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", linkManList);

        return result;
    }

    @RequestMapping(value = "/save11", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(StockOutDtl stockOutDtl) {
        stockOutDtlService.add(stockOutDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(StockOutDtl stockOutDtl) {
        if (stockOutDtl != null && stockOutDtl.getSkuId() > 0) {
            Sku sku = skuService.findById(stockOutDtl.getSkuId());
            if (sku != null) {
                stockOutDtl.setItemName(sku.getItemName());
            }
        }

        stockOutDtlService.update(stockOutDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "ids[]") Integer[] ids) {
        stockOutDtlService.delete(ids);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public StockOutDtl findById(@RequestParam("id") int id) {
        StockOutDtl stockOutDtl = stockOutDtlService.findById(id);
        return stockOutDtl;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(StockOutDtl stockOutDtl, @RequestParam("id") int id)
            throws Exception {
        stockOutDtl.setStockOutId(id);

        int resultTotal = 0; // 操作的记录条数
        if (stockOutDtl.getId() == null) {
            resultTotal = stockOutDtlService.add(stockOutDtl);
        } else {
            resultTotal = stockOutDtlService.update(stockOutDtl);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") Integer[] ids)
            throws Exception {
        if (ids != null && ids.length > 0) {
            stockOutDtlService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

}
