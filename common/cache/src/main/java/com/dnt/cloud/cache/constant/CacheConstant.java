package com.dnt.cloud.cache.constant;

import org.springframework.util.StringUtils;

public interface CacheConstant {

	// 参数分割符
	public static final String P__SEPERATE = ",";

	// List<>
	public static final String K__ALL = "all";

	// OpenServiceDomain
	public static final String NS__SERVICE = "open.service";
	public static final String NS__SERVICE__BY_CODE = NS__SERVICE + ":byCode";
	public static final String NS__SERVICE__BY_ID = NS__SERVICE + ":byID";

	// MIMSServiceDomain
	public static final String NS__MIMS = "nrc.mims";
	public static final String NS__MIMS__BY_CODE = NS__MIMS + ":byCode";
	public static final String NS__MIMS__BY_ID = NS__MIMS + ":byID";

	// OpenApiDomain
	public static final String NS__API = "open.api";
	public static final String NS__API__BY_CODE = NS__API + ":byCode";
	public static final String NS__API__BY_ID = NS__API + ":byID";

	// ApiRouteInfo
	public static final String NS__API_ROUTE = "open.api.route";

	public static final String NS__UCENTER = "open.ucenter";
	// UserDo
	public static final String NS__UCENTER_USER = NS__UCENTER + ":currentuser";// 登录用户
	// String
	public static final String NS__UCENTER_REGIST_VCODE = NS__UCENTER + ":vcode.regist";// 注册验证码
	// String
	public static final String NS__UCENTER_LOGIN_VCODE = NS__UCENTER + ":vcode.login";// 登录验证吗
	// String
	public static final String NS__UCENTER_LOGIN_LOGINERR_COUNT = NS__UCENTER + ":loginerr.count";// 登录错误次数记录

	/**
	 * 
	 * @param params
	 * @return 用默认分隔符（,）分隔的字符串
	 */
	public static String getKey(Object... params) {
		return StringUtils.arrayToDelimitedString(params, P__SEPERATE);
	}

	/**
	 * @param delim
	 *            分隔符
	 * @param params
	 *            参数
	 * @return 用指定分隔符分隔的字符串
	 */
	public static String getKeyWithDelim(String delim, Object... params) {
		return StringUtils.arrayToDelimitedString(params, delim);
	}

}
