package com.dnt.cloud.common.domain.enums;

/**
 * <p>启用标识</p>
 * @author zc
 * @version $Id: EnableFlag.java, v 0.1 2014-5-6 下午6:11:52 zhoucong Exp $
 */
public enum EnableFlag implements EnumBase {

    Y("Y", "Y"),

    N("N", "N");

    private final String code;

    private final String message;

    EnableFlag(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static EnableFlag getByCode(String code) {
        for (EnableFlag resultCode : EnableFlag.values()) {
            if (resultCode.getCode().equals(code)) {
                return resultCode;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
