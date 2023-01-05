package com.dnt.cloud.common.domain.result;

public enum ResultCode {

	SUCCESS("S0001", "处理成功"),

	NOT_EXIST("S0002", "结果不存在"),

	DATA_NOT_FOUND("S0003", "数据没找到"),

	FAIL("F0001", "处理失败"),

	ILLEGAL_SIGN_TYPE("F2001", "签名类型不正确"),

	ILLEGAL_SIGN("F2002", "验签未通过"),

	REMOTE_FAIL("F9999", "远程服务繁忙"),

	PARAM_INVALID("F5002", "参数校验不通过"),

	METHOD_NOT_SUPPORTED("F5003", "错误的请求方法"),

	CONCURRENCY_FAILURE("F5004", "并发错误"),

	DUPLICATE_KEY("F5005", "违反唯一键约束"),

	UNKNOWN_REPORT_FORM_TYPE("F5101", "无法识别的表单类型"),

	FORBIDDEN("F9001", "无操作权限"),

	FORBIDDEN_API("F9002", "无API权限"),

	INNER_EXCEPTION("E0001", "内部异常"),

	CONFIG_ERROR("E0002", "配置错误"),

	OPRATION_TIMEOUT("E0101", "操作超时"),

	SYSTEM_ERROR("SYSTEM_ERROR", "系统内部错误"),

	REQUIRED_FIELD_NOT_EXIST("REQUIRED_FIELD_NOT_EXIST", "必填字段未填写"),

	INPUT_CHARSET_ERROR("INPUT_CHARSET_ERROR", "编码类型错误"),

	APP_NOT_EXIST("APP_NOT_EXIST", "APP不存在"),

	ROUTER_NOT_EXIST("ROUTER_NOT_EXIST", "路由不存在"),

	API_NOT_EXIST("API_NOT_EXIST", "API不存在"),

	API_NOEXIST_NOAUTH("EXTERNALAPP_NOT_EXIST_OR_DISABLE", "API不存在或无权限"),

	USER_API_OPTION_FAIL("USER_API_OPTION_FAIL", "用户API权限操作失败"),

	;

	private final String code;

	private final String message;

	ResultCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ResultCode getByCode(String code) {
		for (ResultCode resultCode : ResultCode.values()) {
			if (resultCode.getCode().equals(code)) {
				return resultCode;
			}
		}
		return null;
	}

	public boolean isSuccess() {
		return code.startsWith("S");
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return String.format("ResultCode[success=%s,code=%s,message=%s]", this.isSuccess(), this.code, this.message);
	}
}
