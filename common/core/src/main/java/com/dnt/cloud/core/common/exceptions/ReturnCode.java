package com.dnt.cloud.core.common.exceptions;

public enum ReturnCode {

	SUCCESS("S0001", "处理成功"),

	NOT_EXIST("S0001", "结果不存在"),

	FAIL("F0001", "处理失败"),

	PARAM_INVALID("F5002", "参数校验不通过"),

	METHOD_NOT_SUPPORTED("F5003", "错误的请求方法"),

	CONCURRENCY_FAILURE("F5004", "并发错误"),

	UNKNOWN_REPORT_FORM_TYPE("F5101", "无法识别的表单类型"),

	FORBIDDEN("F9001", "无操作权限"),

	INNER_EXCEPTION("E0001", "内部异常"),

	CONFIG_ERROR("E0002", "配置错误"),

	OPRATION_TIMEOUT("E0101", "操作超时"),

	ILLEGAL_SIGN_TYPE("ILLEGAL_SIGN_TYPE", "签名类型不正确"),

	SYSTEM_ERROR("SYSTEM_ERROR", "系统内部错误"),

	ILLEGAL_SIGN("ILLEGAL_SIGN", "验签未通过"),

	ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", "参数校验未通过"),

	REQUIRED_FIELD_NOT_EXIST("REQUIRED_FIELD_NOT_EXIST", "必填字段未填写"),

	INPUT_CHARSET_ERROR("INPUT_CHARSET_ERROR", "编码类型错误"),

	APP_NOT_EXIST("APP_NOT_EXIST", "APP不存在"),

	ROUTER_NOT_EXIST("ROUTER_NOT_EXIST", "路由不存在"),

	API_NOT_EXIST("API_NOT_EXIST", "API不存在"),

	ILLEGAL_SERVICE("ILLEGAL_SERVICE", "服务接口不存在"),

	API_NOEXIST_NOAUTH("EXTERNALAPP_NOT_EXIST_OR_DISABLE", "API不存在或无权限"),

	USER_API_OPTION_FAIL("USER_API_OPTION_FAIL", "用户API权限操作失败"),

	FIELD_LENGTH_EXCEEDS_LIMIT("FIELD_LENGTH_EXCEEDS_LIMIT", "字段长度超过限制"),

	ILLEGAL_AMOUNT_FORMAT("ILLEGAL_AMOUNT_FORMAT", "金额格式错误"), 

	INVALID_DATE_NOT_EXIST("INVALID_DATE_NOT_EXIST", "支付过期时间不能为空"),

	ILLEGAL_INVALIDDATE_FORMAT("ILLEGAL_INVALIDDATE_FORMAT", "过期时间格式错误"),

	ILLEGAL_MOBILE_FORMAT("ILLEGAL_MOBILE_FORMAT", "手机格式不合法"),

	FIELD_TYPE_ERROR("FIELD_TYPE_ERROR", "字段类型错误"),
	
	ORDER_NOT_EXIST_ERROR("ORDER_NOT_EXIST_ERROR", "订单不存在"),

	MERCHANT_NOT_EXIST_ERROR("MERCHANT_NOT_EXIST_ERROR", "商户不存在"),
	;

	private final String code;

	private final String message;

	ReturnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ReturnCode getByCode(String code) {
		for (ReturnCode resultCode : ReturnCode.values()) {
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
		return String.format("ResultCode[success=%s,code=%s,message=%s]",
				this.isSuccess(), this.code, this.message);
	}
}
