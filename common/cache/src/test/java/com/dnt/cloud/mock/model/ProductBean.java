package com.dnt.cloud.mock.model;

public class ProductBean {

	private String name;
	private String type;

	public ProductBean() {
	}

	public ProductBean(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
