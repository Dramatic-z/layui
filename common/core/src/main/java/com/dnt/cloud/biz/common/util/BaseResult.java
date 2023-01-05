package com.dnt.cloud.biz.common.util;

import java.io.Serializable;

/**
 * <p>结果对象基类</p>
 * @author sean won
 * @version $Id: BaseResult.java, v 0.1 2009-12-8 上午10:25:14 fuyangbiao Exp $
 */
public class BaseResult implements Serializable {
    private static final long serialVersionUID = -2253380911043438025L;

    /** 是否成功 */
    protected boolean         success          = false;

    /** 错误编码 */
    protected String          errorCode;
    /** 结果返回信息 */
    protected String          resultMessage;

    public BaseResult() {

    }

    public BaseResult(boolean success) {
        this.success = success;
    }

    public BaseResult(boolean success, String returnMessage) {
        this.success = success;
        this.resultMessage = returnMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 子类根据自己需要重写此方法
     * @return
     */
    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
