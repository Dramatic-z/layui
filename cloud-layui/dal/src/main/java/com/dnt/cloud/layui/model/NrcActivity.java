package com.dnt.cloud.layui.model;

import java.io.Serializable;
import java.util.Date;

public class NrcActivity implements Serializable {
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

    private static final long serialVersionUID = 1L;

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
        this.nameChina = nameChina == null ? null : nameChina.trim();
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng == null ? null : nameEng.trim();
    }

    public String getNameFan() {
        return nameFan;
    }

    public void setNameFan(String nameFan) {
        this.nameFan = nameFan == null ? null : nameFan.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getDetailsChina() {
        return detailsChina;
    }

    public void setDetailsChina(String detailsChina) {
        this.detailsChina = detailsChina == null ? null : detailsChina.trim();
    }

    public String getDetailsEng() {
        return detailsEng;
    }

    public void setDetailsEng(String detailsEng) {
        this.detailsEng = detailsEng == null ? null : detailsEng.trim();
    }

    public String getDetailsFan() {
        return detailsFan;
    }

    public void setDetailsFan(String detailsFan) {
        this.detailsFan = detailsFan == null ? null : detailsFan.trim();
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
        this.operator = operator == null ? null : operator.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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
        this.extension = extension == null ? null : extension.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nameChina=").append(nameChina);
        sb.append(", nameEng=").append(nameEng);
        sb.append(", nameFan=").append(nameFan);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", detailsChina=").append(detailsChina);
        sb.append(", detailsEng=").append(detailsEng);
        sb.append(", detailsFan=").append(detailsFan);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", marketId=").append(marketId);
        sb.append(", status=").append(status);
        sb.append(", operator=").append(operator);
        sb.append(", memo=").append(memo);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModify=").append(gmtModify);
        sb.append(", extension=").append(extension);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        NrcActivity other = (NrcActivity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNameChina() == null ? other.getNameChina() == null : this.getNameChina().equals(other.getNameChina()))
            && (this.getNameEng() == null ? other.getNameEng() == null : this.getNameEng().equals(other.getNameEng()))
            && (this.getNameFan() == null ? other.getNameFan() == null : this.getNameFan().equals(other.getNameFan()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
            && (this.getDetailsChina() == null ? other.getDetailsChina() == null : this.getDetailsChina().equals(other.getDetailsChina()))
            && (this.getDetailsEng() == null ? other.getDetailsEng() == null : this.getDetailsEng().equals(other.getDetailsEng()))
            && (this.getDetailsFan() == null ? other.getDetailsFan() == null : this.getDetailsFan().equals(other.getDetailsFan()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getMarketId() == null ? other.getMarketId() == null : this.getMarketId().equals(other.getMarketId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModify() == null ? other.getGmtModify() == null : this.getGmtModify().equals(other.getGmtModify()))
            && (this.getExtension() == null ? other.getExtension() == null : this.getExtension().equals(other.getExtension()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNameChina() == null) ? 0 : getNameChina().hashCode());
        result = prime * result + ((getNameEng() == null) ? 0 : getNameEng().hashCode());
        result = prime * result + ((getNameFan() == null) ? 0 : getNameFan().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getDetailsChina() == null) ? 0 : getDetailsChina().hashCode());
        result = prime * result + ((getDetailsEng() == null) ? 0 : getDetailsEng().hashCode());
        result = prime * result + ((getDetailsFan() == null) ? 0 : getDetailsFan().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getMarketId() == null) ? 0 : getMarketId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModify() == null) ? 0 : getGmtModify().hashCode());
        result = prime * result + ((getExtension() == null) ? 0 : getExtension().hashCode());
        return result;
    }
}