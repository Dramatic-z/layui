package com.dnt.cloud.layui.web.domain;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author dramatic
 */
public class NrcSyncCategoryDomain {

    /**
     * 代码
     */
    @NotNull
    private String code;

    /**
     * 分类名称
     */
    @NotNull
    private String name;

    /**
     * 显示图标
     */
    private String categoryImage;

    /**
     * 启用标记:   Y启用 N禁用
     */
    private String enableFlag;

    /**
     * A:一级
     * B:二级
     * C:三级
     */
    @NotNull
    private String level;

    /**
     * 图片路径；展示图片，动态gif图片，用于展示首页效果
     */
    private String categoryShow;

    /**
     * 是否首页展示
     */
    private String isShow;

    /**
     * 显示顺序
     */
    private Integer sortNum;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类展示图
     */
    private String showPics;

    /**
     * 是否着重展示
     */
    private String isMiddle;

    /**
     * 备注
     */
    private String memo;

    /**
     * 子分类
     */
    private List<NrcSyncCategoryDomain> childList;

    /**
     * 商户ID
     */
    private Long merchantId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategoryShow() {
        return categoryShow;
    }

    public void setCategoryShow(String categoryShow) {
        this.categoryShow = categoryShow;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowPics() {
        return showPics;
    }

    public void setShowPics(String showPics) {
        this.showPics = showPics;
    }

    public String getIsMiddle() {
        return isMiddle;
    }

    public void setIsMiddle(String isMiddle) {
        this.isMiddle = isMiddle;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<NrcSyncCategoryDomain> getChildList() {
        return childList;
    }

    public void setChildList(List<NrcSyncCategoryDomain> childList) {
        this.childList = childList;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
