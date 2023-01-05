package com.dnt.cloud.layui.web.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.dnt.cloud.common.lang.StringUtil;

/**
 * layui 框架专用返回数据格式封装,可重新在config中配置对应的K,V名称,此处使用layui默认K,V。
 *
 * @author Administrator
 */
public class LayuiReturnUtils extends HashMap<String, Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public static String LayuiReturndata(Long count, List<?> data) {
        Map<String, Object> r = new HashMap<String, Object>();
        r.put("code", 0);
        r.put("msg", "");
        r.put("count", count);
        r.put("data", data);
        String str = JSON.toJSONString(r);
        return str;
    }


    public static String LayuiReturndomain(String msg, Object data) {
        Map<String, Object> r = new HashMap<String, Object>();
        r.put("code", 0);
        r.put("msg", msg);
        r.put("data", data);
        String str = JSON.toJSONString(r);
        return str;
    }


    public static String LayuiReturnImageUrl(String data) {
        Map<String, Object> r = new HashMap<String, Object>();
        Map<String, Object> p = new HashMap<String, Object>();
        if (StringUtil.isBlank(data)) {
            r.put("code", 1);
        } else {
            r.put("code", 0);
        }
        r.put("msg", "");
        p.put("src", data);
        r.put("data", p);
        String str = JSON.toJSONString(r);
        return str;
    }

    public static String LayuiReturnLogin(String msg, int code) {
        Map<String, Object> r = new HashMap<String, Object>();
        r.put("code", code);
        r.put("msg", msg);
        String str = JSON.toJSONString(r);
        return str;
    }

	public static String layuiReturnError(String msg) {
		Map<String, Object> r = new HashMap<String, Object>();
		r.put("code", 0);
		r.put("msg", msg);
		String str = JSON.toJSONString(r);
		return str;
	}

}
