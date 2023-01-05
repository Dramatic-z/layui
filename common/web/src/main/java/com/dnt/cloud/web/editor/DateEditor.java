package com.dnt.cloud.web.editor;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 自动转化时间
 * </p>
 * 
 * @author zc
 * @version $Id: DateEditor.java, v 0.1 2014-5-7 上午10:25:36 zhoucong Exp $
 */
public class DateEditor extends PropertyEditorSupport {

	private static final Logger logger = LoggerFactory.getLogger(DateEditor.class);

	private static final String[] supportDatePatterns = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				Date result = DateUtils.parseDate(text, supportDatePatterns);
				if (result != null) {
					setValue(result);
				}
			} catch (Exception e) {
				logger.error("时间格式错误", e);
			}
		}
	}
}
