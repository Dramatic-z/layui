package com.dnt.cloud.common.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NrcResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;

	private String code;

	private String message;

	private String description;

	public NrcResult() {
	}

	public NrcResult(ResultCode resultCode) {
		this(resultCode, null);
	}

	public NrcResult(ResultCode resultCode, String description) {
		if (resultCode != null) {
			this.success = resultCode.isSuccess();
			this.code = resultCode.getCode();
			this.message = resultCode.getMessage();
		}
		this.description = description;
	}

	public static NrcResult success() {
		return new NrcResult(ResultCode.SUCCESS);
	}

	public static NrcResult success(String description) {
		return new NrcResult(ResultCode.SUCCESS, description);
	}

	public static NrcResult fail() {
		return new NrcResult(ResultCode.FAIL);
	}

	public static NrcResult fail(String description) {
		return new NrcResult(ResultCode.FAIL, description);
	}

	public static NrcResult fallback() {
		return new NrcResult(ResultCode.REMOTE_FAIL);
	}

	public static NrcResult invalid() {
		return new NrcResult(ResultCode.PARAM_INVALID);
	}

	public static NrcResult invalid(String description) {
		return new NrcResult(ResultCode.PARAM_INVALID, description);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
