package com.dnt.cloud.cache.result;

public enum ResultCode {

	SUCCESS("0000", "success"),

	FAIL("1000", "fail"),

	DATA_NOT_EXSITS("0001", "data not exist"),

	VALUE_NOT_NULL("2000", "value is null"),

	VALUE_INCR_ERROR("2001", "value is not an integer or out of range"),

	CONN_ERROR("4000", "connection error or timeout"),

	// FIXME
	EXCEPTION("9000", "exception"),

	UNKNOW_ERROR("9999", "unkonw error");

	String code;
	String message;

	ResultCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public boolean isSuccess() {
		return code.startsWith("0");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("ResultCode[success=%s,code=%s,message=%s]", this.isSuccess(), this.code, this.message);
	}
}
