package com.dnt.cloud.layui.query;

import java.util.Date;

import com.dnt.cloud.common.domain.query.PageQuery;

public class NrcActivityQuery extends PageQuery {
	
	private Long id;

    private String nameChina;

    private String nameEng;

	private String nameFan;

    private String imageUrl;

    private String detailsChina;

    private String detailsEng;

	private String detailsFan;

    private Date startTime;

    private Date endTime;

    private Long marketId;

    private Integer status;

    private String operator;

    private String memo;

    private Date gmtCreate;

    private Date gmtModify;

    private String extension;

	public String getNameFan() {
		return nameFan;
	}

	public void setNameFan(String nameFan) {
		this.nameFan = nameFan;
	}

	public String getDetailsFan() {
		return detailsFan;
	}

	public void setDetailsFan(String detailsFan) {
		this.detailsFan = detailsFan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameChina() {
		return nameChina;
	}

	public void setNameChina(String nameChina) {
		this.nameChina = nameChina;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDetailsChina() {
		return detailsChina;
	}

	public void setDetailsChina(String detailsChina) {
		this.detailsChina = detailsChina;
	}

	public String getDetailsEng() {
		return detailsEng;
	}

	public void setDetailsEng(String detailsEng) {
		this.detailsEng = detailsEng;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getMarketId() {
		return marketId;
	}

	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
    
    
    
}