package com.l1.controller;

import com.l1.entity.Dic;
import com.l1.entity.PageBean;
import com.l1.entity.StockOut;
import com.l1.entity.StockOutDtl;
import com.l1.service.BillStatService;
import com.l1.service.DicService;
import com.l1.service.StockOutService;
import com.l1.service.WarehouseService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import com.l1.util.WrappedJSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: luopotaotao
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/stockOut")
public class StockOutController {
    @Resource
    private StockOutService stockOutService;
    @Resource
    private DicService dicService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping
    public String showPage(Model model) {
        List<Dic> statusDic = dicService.query("stockOutStatus");
        model.addAttribute("statusDic",statusDic);
        return "stockOutManage";
    }

    @RequestMapping(value = "/listFinishedForCombo",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<Integer,String>> listFinishedForCombo(){
        List<Map<Integer,String>> ret = stockOutService.findListFinishedForCombo();
        return ret;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, StockOut s_stockOut)
            throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<StockOut> stockOutList = stockOutService.find(map);
        Long total = stockOutService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", stockOutList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(StockOut stockOut,
                                    @RequestParam(value = "inserted",required = false) String insertedStr,
                                    @RequestParam(value = "updated",required = false) String updatedStr,
                                    @RequestParam(value = "deleted[]",required = false) Integer[] deleted, BindingResult err) {
        stockOut.setCreate_time(DateUtil.now());
        List<StockOutDtl> inserted = null;
        List<StockOutDtl> updated = null;
        if(stockOut.getId()!=null&&stockOut.getId()>0){
            if(updatedStr!=null&&updatedStr.length()>0){
                updated = jsonArrayToStockOutDetailList(JSONArray.fromObject(updatedStr));
            }
        }
        if(insertedStr!=null&&insertedStr.length()>0){
            inserted = jsonArrayToStockOutDetailList(JSONArray.fromObject(insertedStr));
        }

        int count;
        if (stockOut.getId() != null) {
            count = stockOutService.updateWithDetails(stockOut, inserted,updated,deleted);
        } else {
            count = stockOutService.saveStockOutWithDetails(stockOut, inserted);

        }
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count > 0);
        return ret;
    }

    private List<StockOutDtl> jsonArrayToStockOutDetailList(JSONArray array){
        List<StockOutDtl> ret = null;
        if(array!=null&&array.size()>0){
            ret = new LinkedList<StockOutDtl>();
            for(int i = 0;i<array.size();i++){
                ret.add(jsonToStockOutDetail(array.getJSONObject(i)));
            }
        }
        return ret;
    }

    private StockOutDtl jsonToStockOutDetail(JSONObject json) {
        WrappedJSON item = new WrappedJSON(json);
        StockOutDtl ret = new StockOutDtl();
        ret.setId(item.getInteger("id"));
        ret.setSkuId(item.getInteger("skuId"));
        ret.setStockOutId(item.getInteger("stockOutId"));
        ret.setStockOutAmount(item.getInteger("stockOutAmount"));

        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(StockOut stockOut) {
        stockOut.setUpdate_time(DateUtil.now());
        stockOutService.update(stockOut);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") Integer[] ids) throws Exception {
        int count = 0;
        if (ids != null && ids.length > 0) {
            count = stockOutService.delete(ids);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", count>0);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public StockOut findById(@RequestParam(value = "id") Integer id) throws Exception {
        StockOut stockOut = stockOutService.findById(id);
        return stockOut;
    }

    @RequestMapping(value = "/finish",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> finish(@RequestParam(value = "ids[]")Integer[] ids,@RequestParam(value = "stockOutWarehouseIds[]")Integer[] stockOutWarehouseIds){
        Map<String,Object> ret = new HashMap<String, Object>();
        int count = stockOutService.finish(ids,stockOutWarehouseIds);
        ret.put("flag",count>0);
        return ret;
    }

    @RequestMapping(value = "/unfinish",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> unfinish(@RequestParam(value = "ids[]")Integer[] ids,@RequestParam(value = "stockOutWarehouseIds[]")Integer[] stockOutWarehouseIds){
        Map<String,Object> ret = new HashMap<String, Object>();
        int count = stockOutService.unfinish(ids,stockOutWarehouseIds);
        ret.put("flag",count>0);
        return ret;
    }
}
