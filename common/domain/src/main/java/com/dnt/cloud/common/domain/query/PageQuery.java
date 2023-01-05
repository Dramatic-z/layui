package com.dnt.cloud.common.domain.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PageQuery {

	int pageNum = 1;

	int pageSize = 10; // 为0时查询全部记录

	String orderBy;
	
	public PageQuery() {
	}

	public PageQuery(int pageSize) {
		this.pageSize = pageSize;
	}

	public PageQuery(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public PageQuery(int pageNum, int pageSize, String orderBy) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.orderBy = orderBy;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
