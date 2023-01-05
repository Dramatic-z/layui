package com.dnt.cloud.mock.model;

public class Product extends ProductBean {

	private Long id;

	public Product() {
	}

	public Product(String name, String type) {
		super(name, type);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
