package com.dnt.cloud.biz.common.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dnt.cloud.common.lang.StringUtil;

/**
 * <p>
 * JDK自带Enum对象处理工具类
 * </p>
 *
 * @author sean won
 * @version $Id: JDKEnumUtil.java, v 0.1 2009-9-18 上午10:33:43 fuyangbiao Exp $
 */
@SuppressWarnings("unchecked")
public class JDKEnumUtil {
    private static final Logger logger            = LoggerFactory.getLogger(JDKEnumUtil.class);

    private static final String GET_METHOD_PREFIX = "get";
	private static String[] enumPackages = {
			"com.dnt.cloud.common.domain.enums."
			, "com.dnt.cloud.mims.enums."
			,"com.dnt.cloud.orders.enums."
			,"com.dnt.cloud.spums.enums."
			,"com.dnt.cloud.report.enums."};

    /**
     * 获取枚举类
     *
     * @param className
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Class<Enum> getEnumClass(String className) {
        Class<Enum> clazz = null;
        for (String enumPackage : enumPackages) {
            try {
                if (StringUtil.contains(className, ".")) {// 针对传全路径类名
                    clazz = (Class<Enum>) Class.forName(className);
                    if (clazz != null && clazz.isInstance(Enum.class)) {
                        return clazz;
                    }
                } else {// 针对类名
                    clazz = (Class<Enum>) Class.forName(enumPackage + className);
                    if (clazz != null && clazz.isInstance(Enum.class)) {
                        return clazz;
                    }
                }
            } catch (Exception e) {

            }
        }
        return clazz;
    }

    /**
     * 获取枚举列表
     *
     * @param <T>
     * @param className
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List<Enum> getEnumList(String className) {
        Class<Enum> clazz = (Class<Enum>) getEnumClass(className);
        List<Enum> enumList = new ArrayList<Enum>();
        try {
            for (Enum e : clazz.getEnumConstants()) {
                enumList.add(e);
            }
        } catch (Exception e) {
            logger.error("加载枚举失败：" + className);
            logger.error("", e);
        }
        return enumList;
    }

    /**
     * 通过枚举名称获取枚举
     *
     * @param className
     * @param name
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Enum getByName(String className, String name) {
        Class<Enum> c = getEnumClass(className);

        if (c != null) {
            try {
                return Enum.valueOf(c, name);
            } catch (Exception e) {
                logger.error("加载枚举失败：" + className);
                logger.error("", e);
            }
        }

        return null;
    }




    /**
     * 获取属性方法名
     *
     * @param propertyName
     * @return
     */
    private static String propertyGetMethodName(String propertyName) {
        return GET_METHOD_PREFIX + StringUtil.substring(propertyName, 0, 1).toUpperCase()
               + StringUtil.substring(propertyName, 1, propertyName.length());
    }
}
