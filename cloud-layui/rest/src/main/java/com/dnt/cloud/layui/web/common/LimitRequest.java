package com.dnt.cloud.layui.web.common;

import java.lang.annotation.*;

/**
 * @author dramatic
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRequest {

    //毫秒，分钟，小时 之间的转换用算数
    long time() default 1000*60*10; // 限制时间 单位：毫秒
    int count() default 100; // 允许请求的次数

}
