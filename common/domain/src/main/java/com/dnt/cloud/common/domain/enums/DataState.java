package com.dnt.cloud.common.domain.enums;

/**
 * 数据状态
 * 待审核-W,审核通过-A，审核拒绝-D
 * @author hazyhao
 *
 */
public enum DataState implements EnumBase {

	WAIT("W", "WAIT"),
	APPROVE("A", "APPROVE"),
    DENY("D", "DENY");

    private final String code;

    private final String message;

    DataState(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static DataState getByCode(String code) {
        for (DataState resultCode : DataState.values()) {
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
