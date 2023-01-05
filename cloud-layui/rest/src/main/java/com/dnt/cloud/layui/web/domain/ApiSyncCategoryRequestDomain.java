package com.dnt.cloud.layui.web.domain;

import javax.validation.constraints.NotNull;

/**
 * @author dramatic
 */
public class ApiSyncCategoryRequestDomain {

    /**
     * appid
     */
    @NotNull
    private Long appid;

    /**
     * sign
     */
    @NotNull
    private String sign;

    /**
     * 代码
     */
    @NotNull
    private NrcSyncCategoryDomain categoryDomain;

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public NrcSyncCategoryDomain getCategoryDomain() {
        return categoryDomain;
    }

    public void setCategoryDomain(NrcSyncCategoryDomain categoryDomain) {
        this.categoryDomain = categoryDomain;
    }
}
