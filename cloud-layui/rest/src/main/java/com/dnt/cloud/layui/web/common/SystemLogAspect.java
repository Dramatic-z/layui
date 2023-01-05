package com.dnt.cloud.layui.web.common;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


/**
 * @author dramatic
 */
@Aspect
@Component
public class SystemLogAspect {

    //注入Service用于把日志保存数据库

    /**
     * 本地异常日志记录对象
     */
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Service层切点
    @Pointcut("@annotation(com.dnt.cloud.layui.web.common.SystemServiceLog)")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.dnt.cloud.layui.web.common.SystemControllerLog)")
    public void controllerAspect() {
    }

    private String argClass = "StandardSessionFacade,RequestFacade,ModelAndView,BindingAwareModelMap,BindingResult,BeanPropertyBindingResult";

    /**
     * 用于拦截Controller层记录用户的操作  before
     *
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
    }

    /**
     * 用于拦截Controller层记录用户的操作 error
     *
     * @param joinPoint
     */
    @AfterThrowing("controllerAspect()")
    public void afterThrowing(JoinPoint joinPoint) {
        //System.out.println("异常通知");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
    }

    /**
     * 用于拦截Controller层记录用户的操作 after
     *
     * @param joinPoint
     */
    @After("controllerAspect()")
    public void finalMethod(JoinPoint joinPoint) {
        //System.out.println("最终通知");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */

    @SuppressWarnings("rawtypes")
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 解决权限含义问题
     *
     * @param identy
     * @return
     */
    public String setMemo(Long identy) {
        String msg = null;
        if (identy != null) {
            switch (identy.intValue()) {
                case 1:
                    msg = "管理员";
                    break;
                case 2:
                    msg = "操作人员";
                    break;
                case 3:
                    msg = "报表人员";
                    break;
                case -1:
                    msg = "版本人员";
                    break;
                case -2:
                    msg = "超级管理员";
                    break;
                default:
                    msg = "未知";
                    break;
            }
        } else {
            msg = "无用户";
        }
        return msg;
    }
}



