package com.dnt.cloud.service.exception;

import com.dnt.cloud.common.lang.exception.ChainedException;

/**
 * <p>代表一个通用的Service的异常。</p>
 * @author sean won
 * @version $Id: ServiceException.java, v 0.1 2009-9-25 上午11:35:12 fuyangbiao Exp $
 */
public class ServiceException extends ChainedException {
    private static final long serialVersionUID = 3427328890517025634L;

    /**
     * 创建一个异常。
     */
    public ServiceException() {
        super();
    }

    /**
     * 创建一个异常。
     *
     * @param message 异常信息
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * 创建一个异常。
     *
     * @param message 异常信息
     * @param cause 异常原因
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 创建一个异常。
     *
     * @param cause 异常原因
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
