package com.dnt.cloud.biz.common.util;

import java.util.HashMap;
import java.util.Map;

import com.dnt.cloud.common.lang.StringUtil;

/**
 * <p>参数转换器</p>
 * @author sean won
 * @version $Id: PropertyConverter.java, v 0.1 2010-6-11 下午02:46:08 fuyangbiao Exp $
 */
public class PropertyConverter {
    private static final String DEFAULT_EQUALS = "=";
    private static final String DEFAULT_SPLIT  = ";";

    /**
     * 将MAP转换为字符串
     * @param propertyMap
     * @return
     */
    public static String convertFromMap(Map<String, String> propertyMap) {
        return convertFromMap(propertyMap, DEFAULT_EQUALS, DEFAULT_SPLIT);
    }

    /**
     * 将MAP转换为字符串
     * @param propertyMap
     * @param equalsTag 等于符
     * @param splitTag 分隔符
     * @return
     */
    public static String convertFromMap(Map<String, String> propertyMap, String equalsTag,
                                        String splitTag) {
        if (propertyMap == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
            sb.append(entry.getKey()).append(equalsTag).append(entry.getValue()).append(splitTag);
        }

        return sb.toString();
    }

    /**
     * 将字符串串转换为MAP
     * @param propertyString
     * @return
     */
    public static Map<String, String> convertToMap(String propertyString) {
        return convertToMap(propertyString, DEFAULT_EQUALS, DEFAULT_SPLIT);
    }

    /**
     * 将字符串串转换为MAP
     * @param propertyString
     * @param equalsTag 等于符
     * @param splitTag 分隔符
     * @return
     */
    public static Map<String, String> convertToMap(String propertyString, String equalsTag,
                                                   String splitTag) {
        Map<String, String> propertyMap = new HashMap<String, String>();
        if (StringUtil.isBlank(propertyString)) {
            return propertyMap;
        }

        String[] propertyArray = propertyString.split(splitTag);
        for (int i = 0; i < propertyArray.length; i++) {
            String[] keyValue = propertyArray[i].split(equalsTag);
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("参数不合法");
            }

            propertyMap.put(keyValue[0], keyValue[1]);
        }

        return propertyMap;
    }
}
