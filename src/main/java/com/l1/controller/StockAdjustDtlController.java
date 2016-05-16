package com.l1.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.l1.entity.StockAdjustDtl;
import com.l1.service.BillStatService;
import com.l1.service.StockAdjustDtlService;
import com.l1.service.SkuService;

/**
 * @Description:
 * @Author: hongxp
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/stockAdjustDtl")
public class StockAdjustDtlController {
    @Resource
    private StockAdjustDtlService stockAdjustDtlService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping("/findAllById")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "id", required = false) Integer id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        List<StockAdjustDtl> linkManList = stockAdjustDtlService.find(map);
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", linkManList);

        return result;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(StockAdjustDtl stockAdjustDtl) {
        stockAdjustDtlService.update(stockAdjustDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "ids[]") Integer[] ids) {
        stockAdjustDtlService.deleteByDtlIds(ids);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public StockAdjustDtl findById(@RequestParam("id") int id) {
        StockAdjustDtl stockAdjustDtl = stockAdjustDtlService.findByDtlId(id);
        return stockAdjustDtl;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(StockAdjustDtl stockAdjustDtl, @RequestParam("id") int id) throws Exception {
        stockAdjustDtl.setId(id);

        int resultTotal = 0; // 操作的记录条数
        if (stockAdjustDtl.getDtlId() == null) {
            resultTotal = stockAdjustDtlService.add(stockAdjustDtl);
        } else {
            resultTotal = stockAdjustDtlService.update(stockAdjustDtl);
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
    public Map<String, Object> delete(@RequestParam(value = "ids[]") Integer[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            stockAdjustDtlService.deleteByDtlIds(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

}
