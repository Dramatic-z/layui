package com.dnt.cloud.common.domain.result;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NrcDataResult<D> extends NrcResult {

	private static final long serialVersionUID = 1L;

	private D data;

	public NrcDataResult() {
	}

	public NrcDataResult(ResultCode resultCode) {
		this(resultCode, null, null);
	}

	public NrcDataResult(ResultCode resultCode, D data) {
		this(resultCode, data, null);
	}

	public NrcDataResult(ResultCode resultCode, D data, String description) {
		super(resultCode, description);
		this.data = data;
	}

	public static <D> NrcDataResult<D> success(String description, D data) {
		return new NrcDataResult<D>(ResultCode.SUCCESS, data, description);
	}

	public static <D> NrcDataResult<D> success(D data) {
		return new NrcDataResult<D>(ResultCode.SUCCESS, data);
	}

	public static NrcDataResult<?> success() {
		return new NrcDataResult<>(ResultCode.SUCCESS, null, null);
	}

	public static NrcDataResult<PageData<?>> notExist() {
		return new NrcDataResult<>(ResultCode.NOT_EXIST, PageData.emptyPageData(Object.class));
	}

	public static <D> NrcDataResult<D> notExistData() {
		return new NrcDataResult<>(ResultCode.NOT_EXIST);
	}

	public static <D> NrcDataResult<D> fail(D data) {
		return new NrcDataResult<D>(ResultCode.FAIL, data);
	}

	public static NrcDataResult<?> fail(String description) {
		return new NrcDataResult<>(ResultCode.FAIL, null, description);
	}

	public static <D> NrcDataResult<D> fail(String desciption, D data) {
		return new NrcDataResult<D>(ResultCode.FAIL, data, desciption);
	}

	public static <D> NrcDataResult<D> fallback(D data) {
		return new NrcDataResult<D>(ResultCode.REMOTE_FAIL, data);
	}

	public static <D> NrcDataResult<D> fallback(D data, String description) {
		return new NrcDataResult<D>(ResultCode.REMOTE_FAIL, data, description);
	}

	public static NrcDataResult<?> invalid() {
		return new NrcDataResult<>(ResultCode.PARAM_INVALID);
	}

	public static NrcDataResult<?> invalid(String description) {
		return new NrcDataResult<>(ResultCode.PARAM_INVALID, null, description);
	}

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String toShortString() {
		return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.setExcludeFieldNames(new String[] { "data" }).append("data", "...").toString();
	}

}
