package com.dnt.cloud.layui.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * layui 框架专用返回数据格式封装,可重新在config中配置对应的K,V名称,此处使用layui默认K,V。
 * @author Administrator
 *
 */
public class EscapeDomainUtils extends HashMap<String,Object>{
	
	private final static Logger logger = LoggerFactory.getLogger(EscapeDomainUtils.class);
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public static Object escapeDomaindata(Object domain){
		
		Field[] field = domain.getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
				String name = field[i].getName();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				String type = field[i].getGenericType().toString();
				if (type.equals("class java.lang.String")) {
					Method m = null;
					String value = null;
					try {
						m = domain.getClass().getMethod("get" + name);
						value = (String) m.invoke(domain);
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("(对象)转义字符出错");
						return domain;
					}
					if(StringUtils.isNotBlank(value)){
						try {
							m = domain.getClass().getDeclaredMethod("set" + name,String.class);
							String repval =StringEscapeUtils.unescapeHtml(value);
							repval = repval.replaceAll("&middot;", "·");
							m.invoke(domain,repval);
						} catch (Exception e) {
							e.printStackTrace();
							logger.info("(对象)转义字符出错");
							return domain;
						}
//						String repval = value.replaceAll("&", "&amp;").replaceAll("'", "&apos;")
//								.replaceAll("\"", "&quot;").replaceAll(">", "&gt;").replaceAll("<", "&lt;");
					}else{
						try {
							m = domain.getClass().getDeclaredMethod("set" + name,String.class);
							m.invoke(domain,"");
						} catch (Exception e) {
							e.printStackTrace();
							logger.info("(对象)转义字符出错");
							return domain;
						}
					}
				}
		}
	   return domain;
    }

	/**
	 * 单个字符转义
	 * @return
	 */
	public static  String escape(String value){
		return StringEscapeUtils.unescapeHtml(value);
	}
	
}
