package com.dnt.cloud.core.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class ListUtil {

	/**
	 * list所有字段后附加append，然后用join方法，separater为分隔符，忽略其中的null值
	 */
	public static <T> String appendJoin(Collection<T> list, String append) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (Iterator<T> it = list.iterator(); it.hasNext();) {
			T t = it.next();
			if (t != null) {
				sb.append(t.toString()).append(append);
			}
		}
		return sb.toString();
	}

	public static <T> Collection<T> removeNull(Collection<T> list) {
		if (list == null) {
			return null;
		}
		Iterator<T> iter = list.iterator();
		while (iter.hasNext()) {
			if (iter.next() == null) {
				iter.remove();
			}
		}
		return list;
	}

	public static List<String> parseString(String param) {
		if (StringUtils.isBlank(param)) {
			return new ArrayList<String>();
		}
		return Arrays.asList(StringUtils.split(param, ','));
	}

	public static <T> String toString(List<T> param) {
		if (param == null || param.size() == 0) {
			return null;
		}
		return ListUtil.appendJoin(param, ",");
	}
	
}
