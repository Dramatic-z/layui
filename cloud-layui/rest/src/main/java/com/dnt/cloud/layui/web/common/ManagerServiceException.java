package com.dnt.cloud.layui.web.common;

import com.dnt.cloud.core.common.exceptions.ReturnCode;

public class ManagerServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ManagerServiceException(String message) {
        super(message);
        this.returnCode = ReturnCode.INNER_EXCEPTION;
    }

    public ManagerServiceException(ReturnCode returnCode) {
        super(returnCode.getMessage());
        this.returnCode = returnCode;
    }

    public ManagerServiceException(ReturnCode returnCode, String message) {
        super(message);
        this.returnCode = returnCode;
    }

    public ManagerServiceException(ReturnCode returnCode, Throwable e) {
        super(returnCode.getMessage(), e);
        this.returnCode = returnCode;
    }

    public ManagerServiceException(ReturnCode returnCode, String message, Throwable e) {
        super(message, e);
        this.returnCode = returnCode;
    }

    final ReturnCode returnCode;

    public ReturnCode getReturnCode() {
        return returnCode;
    }

}
