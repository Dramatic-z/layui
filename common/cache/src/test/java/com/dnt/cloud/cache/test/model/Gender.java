package com.dnt.cloud.cache.test.model;

public enum Gender {

	MALE("M", "男"), FEMAIL("F", "女");

	String code;

	String desc;

	Gender(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return this.code + "-" + desc;
	}
}
