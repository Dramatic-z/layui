package com.dnt.cloud.cache.result;

public class CacheResult<V> {

	private ResultCode resultCode;

	private V value;

	public CacheResult(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public CacheResult(ResultCode resultCode, V value) {
		this.resultCode = resultCode;
		this.value = value;
	}

	public boolean isSuccess() {
		return resultCode.isSuccess();
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String toString() {
		return String.format("CacheResult[resultCode=%s,value=%s]", this.resultCode, this.value);
	}
}
