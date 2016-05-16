package com.l1.util;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import java.math.BigDecimal;

/**
 * Created by luopotaotao on 2016/5/14.
 */
public class WrappedJSON {
    private JSONObject source;

    public WrappedJSON(JSONObject source) {
        this.source = source;
    }

    public String getString(String key) {
        return isAvailable(key) ? null : source.getString(key);
    }

    public Integer getInteger(String key) {
        return isAvailable(key) ? null : source.getInt(key);
    }

    public Double getDouble(String key) {
        return isAvailable(key) ? null : source.getDouble(key);
    }

    public BigDecimal getBigDecimal(String key) {
        return isAvailable(key) ? null : BigDecimal.valueOf(source.getDouble(key));
    }

    private boolean isAvailable(String key){
        Object val = source.get(key);
        return val==null||val instanceof JSONNull || (val instanceof String && ((String) val).isEmpty());
    }
}
