package com.dnt.cloud.core.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dnt.cloud.common.lang.StringUtil;

public class MapUtil {

    public static <T, U> String toString(Map<T, U> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        return JSON.toJSONString(map);
    }

    public static boolean canParse(String mapString) {
        if (StringUtils.isBlank(mapString)) {
            return true;
        }
        try {
            JSON.parseObject(mapString, new TypeReference<Map<String, String>>() {
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 转换JSON字符串为Map格式，如果字符串为空则返回空HashMap对象
     * @param mapString
     * @return Map<String, Map<String, String>>
     */
    public static Map<String, Map<String, String>> parseMapString(String mapString) {
        if (StringUtils.isBlank(mapString) || StringUtils.equalsIgnoreCase("null", mapString)) {
            return new TreeMap<String, Map<String, String>>();
        }
        try {
            return JSON.parseObject(mapString,
                new TypeReference<Map<String, Map<String, String>>>() {
                });
        } catch (Exception e) {
            throw new RuntimeException("JSON格式Map格式不符", e);
        }
    }

    /**
     * MAP转换成JSON
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return JSON.toJSONString(map, SerializerFeature.UseISO8601DateFormat);
    }

    /**
     * MAP转换成JSON
     * @param map
     * @return
     */
    public static String mapMapToJson(Map<String, Map<String, String>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return JSON.toJSONString(map, SerializerFeature.UseISO8601DateFormat);
    }

    /**
     * 转换JSON字符串为Map格式，如果字符串为空则返回空HashMap对象
     * @param mapString
     * @return
     */
    public static Map<String, String> parseString(String mapString) {
        if (StringUtils.isBlank(mapString) || StringUtils.equalsIgnoreCase("null", mapString)) {
            return new HashMap<String, String>();
        }
        try {
            return JSON.parseObject(mapString, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("JSON格式Map格式不符", e);
        }
    }

    /**
     * 判断container是否包含targets全部内容，包括key和value
     */
    public static boolean contains(Map<String, String> container, Map<String, String> targets) {
        if (targets == null || targets.size() == 0) {
            return true;
        } else if (container == null || container.size() == 0) {
            return false;
        } else {
            String key = null;
            for (Iterator<Entry<String, String>> it = targets.entrySet().iterator(); it.hasNext();) {
                Entry<String, String> entry = it.next();
                key = entry.getKey();
                if (!container.containsKey(key)
                    || !StringUtils.equals(entry.getValue(), container.get(key))) {
                    return false;
                }
            }
            return true;
        }
    }

    /** 返回map中第一个，str包含key的value，不存在返回null */
    public static <T> T firstKeyContain(String str, Map<String, T> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        for (Iterator<Entry<String, T>> it = map.entrySet().iterator(); it.hasNext();) {
            Entry<String, T> entry = it.next();
            if (StringUtils.contains(str, entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> void trim(Map<String, T> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        Iterator<Entry<String, T>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, T> entry = iter.next();
            if (entry.getValue() instanceof String) {
                entry.setValue((T) StringUtil.trim((String) entry.getValue()));
            }
        }
    }

    /**
     * 组装MAP KEY字符串,格式:xxx,xxx,xxx
     * @param map
     * @return
     */
    public static String parseKey(Map<String, String> map) {
        StringBuffer res = new StringBuffer();
        if (map == null || map.size() == 0) {
            return null;
        }
        Iterator<Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = iter.next();
            res.append(entry.getKey()).append(",");
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    /**
     * Map<String, String>转换为Map<String, Object>
     * @param params
     * @return
     */
    public static Map<String, Object> mapType(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return null;
        }
        Map<String, Object> newMap = new HashMap<String, Object>();
        for (Entry<String, String> entry : params.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue());
        }
        return newMap;
    }
    
    /**
     * eg:{loginName=ydz_login, userName=yindezhou, pwd=123456}————>loginName=ydz_login&pwd=123456&userName=yindezhou
     * <p>Map参数组装为&连接</p>
     * @param sortedParams
     * @return
     */
    public static String buildHttpParams(Map<String, String> sortedParams) {
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(sortedParams.keySet());
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = String.valueOf(sortedParams.get(key));
			if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
				content.append((index == 0 ? "" : "&") + key + "=" + value);
				index++;
			}
		}
		return content.toString();
	}
    
    public static Map<String,Object> getMapValue(Object thisObj) {
        Map<String,Object> map = new HashMap<String,Object>();
        Class<?> c;
        try {
            c = Class.forName(thisObj.getClass().getName());
            Method[] m = c.getMethods();
            for (int i = 0; i < m.length; i++) {
                String method = m[i].getName();
                if (method.startsWith("get")) {
                    try {
                        Object value = m[i].invoke(thisObj);
                        if (value != null) {
                            String key = method.substring(3);
                            key = key.substring(0, 1).toLowerCase() + key.substring(1);
                            map.put(key, value);
                        }else{
                            value = "";
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Object2Map异常！", e);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Object2Map异常！", e);
        }
        return map;
    }
}
