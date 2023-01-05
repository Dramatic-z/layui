package com.dnt.cloud.core.common.exceptions;

/**
 * <p>校验异常</p>
 * @author zc
 * @version $Id: ValidateException.java, v 0.1 2013-11-13 下午4:28:20 zhoucong Exp $
 */
public class ValidateException extends NRCException {

    private static final long serialVersionUID = 1L;

    public ValidateException(ReturnCode returnCode, String memo, Throwable e) {
        super(returnCode, e);
        this.memo = memo;
    }

    public ValidateException(ReturnCode returnCode, String memo) {
        this(returnCode, memo, null);
    }

    public ValidateException(ReturnCode returnCode, Throwable e) {
        this(returnCode, null, e);
    }

    public ValidateException(ReturnCode returnCode) {
        this(returnCode, null, null);
    }

    final String memo;

    public String getMemo() {
        return memo;
    }

}
