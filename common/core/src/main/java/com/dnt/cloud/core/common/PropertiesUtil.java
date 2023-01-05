package com.dnt.cloud.core.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 资源工具类
 * </p>
 * 
 * @author hazyhao
 *
 */
public class PropertiesUtil {
	static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	public static Map<String, String> readProperties(String fileName) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isBlank(fileName)) {
			return map;
		}
		File pFile = new File(fileName);
		FileInputStream pInStream = null;
		Properties p = null;
		try {
			pInStream = new FileInputStream(pFile);
			p = new Properties();
			p.load(pInStream); // Properties 对象已生成，包括文件中的数据
			pInStream.close();
		} catch (IOException e) {
			logger.debug("读取资源文件异常：", e);
		}

		Enumeration<?> enu = p.propertyNames(); // 取出所有的key

		while (enu.hasMoreElements())

		{
			String key = enu.nextElement().toString();
			map.put(key, p.getProperty(key).toString());
		}
		return map;
	}
}
