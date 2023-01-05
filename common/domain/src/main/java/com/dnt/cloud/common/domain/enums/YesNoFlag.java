package com.dnt.cloud.common.domain.enums;

/**
 * <p>是否标识</p>
 * @author zc
 * @version $Id: EnableFlag.java, v 0.1 2014-5-6 下午6:11:52 zhoucong Exp $
 */
public enum YesNoFlag implements EnumBase {

    Yes("Y", "Y"),

    No("N", "N");

    private final String code;

    private final String message;

    YesNoFlag(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static YesNoFlag getByCode(String code) {
        for (YesNoFlag resultCode : YesNoFlag.values()) {
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
