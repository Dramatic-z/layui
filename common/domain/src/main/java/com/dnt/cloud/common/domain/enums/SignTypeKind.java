package com.dnt.cloud.common.domain.enums;


/**
 * <p>签名类型</p>
 * @author leelun
 * @version $Id: SignTypeKind.java, v 0.1 2013-9-2 下午2:29:35 lilun Exp $
 */
public enum SignTypeKind implements EnumBase {

    MD5("MD5", "MD5"),

    RSA("RSA", "RSA"),

    DSA("DSA", "DSA"),

    ;

    private final String code;

    private final String message;

    private SignTypeKind(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static SignTypeKind getByCode(String code) {
        for (SignTypeKind ls : SignTypeKind.values()) {
            if (ls.code.equalsIgnoreCase(code)) {
                return ls;
            }
        }
        return null;
    }

    public boolean equals(String code) {
        return getCode().equalsIgnoreCase(code);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
