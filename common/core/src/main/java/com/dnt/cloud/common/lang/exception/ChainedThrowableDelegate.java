package com.dnt.cloud.common.lang.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * <p>可嵌套异常代理</p>
 * @author sean won
 * @version $Id: ChainedThrowableDelegate.java, v 0.1 2009-9-18 上午10:26:52 fuyangbiao Exp $
 */
@SuppressWarnings("unchecked")
public class ChainedThrowableDelegate implements ChainedThrowable {
    /** 表示异常不存在的常量. */
    protected static final Throwable NO_CAUSE           = new Throwable();

    /** 常见的用来取得异常起因的方法名. */
    private static final String[]    CAUSE_METHOD_NAMES = { "getNested", "getNestedException",
            "getNextException", "getTargetException", "getException", "getSourceException",
            "getCausedByException", "getRootCause", "getCause" };

    /** 常见的用来取得异常起因的字段名. */
    private static final String[]    CAUSE_FIELD_NAMES  = { "detail" };

    /** 被代理的<code>Throwable</code>对象. */
    protected Throwable              delegatedThrowable;

    /**
     * 创建一个<code>Throwable</code>代理.
     *
     * @param throwable 被代理的异常
     */
    public ChainedThrowableDelegate(Throwable throwable) {
        this.delegatedThrowable = throwable;
    }

    /**
     * 取得被代理的异常的起因.
     *
     * @return 异常的起因.
     */
    public Throwable getCause() {
        Throwable cause = getCauseByWellKnownTypes(delegatedThrowable);

        for (Class throwableClass = delegatedThrowable.getClass(); (cause == null)
                                                                   && Throwable.class
                                                                       .isAssignableFrom(throwableClass); throwableClass = throwableClass
            .getSuperclass()) {
            // 尝试常见的方法
            for (int i = 0; (cause == null) && (i < CAUSE_METHOD_NAMES.length); i++) {
                cause = getCauseByMethodName(delegatedThrowable, throwableClass,
                    CAUSE_METHOD_NAMES[i]);
            }

            // 尝试常见的字段
            for (int i = 0; (cause == null) && (i < CAUSE_FIELD_NAMES.length); i++) {
                cause = getCauseByFieldName(delegatedThrowable, throwableClass,
                    CAUSE_FIELD_NAMES[i]);
            }
        }

        if (cause == delegatedThrowable) {
            cause = null;
        }

        if (cause == NO_CAUSE) {
            return null;
        }

        return cause;
    }

    /**
     * 取得常见<code>Throwable</code>类的异常起因.
     *
     * @param throwable 异常
     *
     * @return 异常起因
     */
    protected Throwable getCauseByWellKnownTypes(Throwable throwable) {
        Throwable cause = null;
        boolean isWellKnownType = false;

        if (throwable instanceof ChainedThrowable) {
            isWellKnownType = true;
            cause = ((ChainedThrowable) throwable).getCause();
        } else if (throwable instanceof SQLException) {
            isWellKnownType = true;
            cause = ((SQLException) throwable).getNextException();
        } else if (throwable instanceof InvocationTargetException) {
            isWellKnownType = true;
            cause = ((InvocationTargetException) throwable).getTargetException();
        } else if (throwable instanceof RemoteException) {
            isWellKnownType = true;
            cause = ((RemoteException) throwable).detail;
        }

        if (isWellKnownType && (cause == null)) {
            return NO_CAUSE;
        }

        return cause;
    }

    /**
     * 通过常见的方法动态地取得异常起因.
     *
     * @param throwable 异常
     * @param throwableClass 异常类
     * @param methodName 方法名
     *
     * @return 异常起因或<code>NO_CAUSE</code>
     */
    protected Throwable getCauseByMethodName(Throwable throwable, Class throwableClass,
                                             String methodName) {
        Method method = null;

        try {
            method = throwableClass.getMethod(methodName, new Class[0]);
        } catch (NoSuchMethodException ignored) {
        }

        if ((method != null) && Throwable.class.isAssignableFrom(method.getReturnType())) {
            Throwable cause = null;

            try {
                cause = (Throwable) method.invoke(throwable, new Object[0]);
            } catch (IllegalAccessException ignored) {
            } catch (IllegalArgumentException ignored) {
            } catch (InvocationTargetException ignored) {
            }

            if (cause == null) {
                return NO_CAUSE;
            }

            return cause;
        }

        return null;
    }

    /**
     * 通过常见的方法动态地取得异常起因.
     *
     * @param throwable 异常
     * @param throwableClass 异常类
     * @param fieldName 字段名
     *
     * @return 异常起因或<code>NO_CAUSE</code>
     */
    protected Throwable getCauseByFieldName(Throwable throwable, Class throwableClass,
                                            String fieldName) {
        Field field = null;

        try {
            field = throwableClass.getField(fieldName);
        } catch (NoSuchFieldException ignored) {
        }

        if ((field != null) && Throwable.class.isAssignableFrom(field.getType())) {
            Throwable cause = null;

            try {
                cause = (Throwable) field.get(throwable);
            } catch (IllegalAccessException ignored) {
            } catch (IllegalArgumentException ignored) {
            }

            if (cause == null) {
                return NO_CAUSE;
            }

            return cause;
        }

        return null;
    }

    /**
     * 打印调用栈到标准错误.
     */
    public void printStackTrace() {
        ExceptionHelper.printStackTrace(this);
    }

    /**
     * 打印调用栈到指定输出流.
     *
     * @param stream 输出字节流.
     */
    public void printStackTrace(PrintStream stream) {
        ExceptionHelper.printStackTrace(this, stream);
    }

    /**
     * 打印调用栈到指定输出流.
     *
     * @param writer 输出字符流.
     */
    public void printStackTrace(PrintWriter writer) {
        ExceptionHelper.printStackTrace(this, writer);
    }

    /**
     * 打印异常的调用栈, 不包括起因异常的信息.
     *
     * @param writer 打印到输出流
     */
    public void printCurrentStackTrace(PrintWriter writer) {
        if (delegatedThrowable instanceof ChainedThrowable) {
            ((ChainedThrowable) delegatedThrowable).printCurrentStackTrace(writer);
        } else {
            delegatedThrowable.printStackTrace(writer);
        }
    }
}
