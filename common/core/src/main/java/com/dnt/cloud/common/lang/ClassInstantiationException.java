package com.dnt.cloud.common.lang;

import com.dnt.cloud.common.lang.exception.ChainedException;

/**
 * <p>代表实例化类时失败的异常。</p>
 * @author sean won
 * @version $Id: ClassInstantiationException.java, v 0.1 2009-9-18 上午10:26:14 fuyangbiao Exp $
 */
public class ClassInstantiationException extends ChainedException {

    private static final long serialVersionUID = 3929753760503304406L;

    /**
     * 构造一个空的异常.
     */
    public ClassInstantiationException() {
        super();
    }

    /**
     * 构造一个异常, 指明异常的详细信息.
     *
     * @param message 详细信息
     */
    public ClassInstantiationException(String message) {
        super(message);
    }

    /**
     * 构造一个异常, 指明引起这个异常的起因.
     *
     * @param cause 异常的起因
     */
    public ClassInstantiationException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造一个异常, 指明引起这个异常的起因.
     *
     * @param message 详细信息
     * @param cause 异常的起因
     */
    public ClassInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
