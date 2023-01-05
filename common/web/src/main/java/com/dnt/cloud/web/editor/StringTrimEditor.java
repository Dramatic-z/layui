package com.dnt.cloud.web.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * string 自动trim
 * <p>
 * 注释
 * </p>
 * 
 * @author liuzy
 * @version $Id: StringTrimEditor.java, v 0.1 2016年9月21日 下午1:33:15 liuzhiyong
 *          Exp $
 */
public class StringTrimEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {
		setValue(StringUtils.hasText(text) ? HtmlUtils.htmlEscape(StringUtils.trimWhitespace(text)) : null);
	}

}
