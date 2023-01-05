package com.dnt.cloud.logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dnt.cloud.common.domain.LogPrefixFactory;
import com.dnt.cloud.common.domain.result.NrcDataResult;
import com.dnt.cloud.common.domain.result.PageData;

public abstract class LoggerAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggerAspect4Controller.class);

	private static Map<String, Logger> loggerCache = new HashMap<>();

	/**
	 * 在子类定义@Pointcut切点
	 */
	public abstract void loggerAspect();

	/**
	 * 方法前置
	 * 
	 * @param joinPoint
	 */
	@Before("loggerAspect()")
	public final void before(JoinPoint joinPoint) {
		try {
			this.doRequestLog(getLogPrefix(), joinPoint);
		} catch (Exception e) {
			logger.warn("记请求日志异常", e);
		}
	}

	/**
	 * 返回后置
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(value = "loggerAspect()", returning = "result")
	public final void afterReturning(JoinPoint joinPoint, Object result) {
		try {
			this.doResponseLog(getLogPrefix(), joinPoint, result);
		} catch (Exception e) {
			logger.warn("记返回日志异常", e);
		}
	}

	/**
	 * 日志前缀
	 * 
	 * @param joinPoint
	 * @return
	 */
	public static String getLogPrefix() {
		return LogPrefixFactory.getLogPrefix();
	}

	/**
	 * 获取缓存的logger实例
	 * 
	 * @param joinPoint
	 * @return
	 */
	protected static final Logger getLogger(JoinPoint joinPoint) {
		String name = joinPoint.getTarget().getClass().getName();
		Logger logger = loggerCache.get(name);
		if (logger == null) {
			loggerCache.put(name, LoggerFactory.getLogger(name));
		}
		return loggerCache.get(name);
	}

	/**
	 * 在此方法中打印请求（方法）参数日志
	 * 
	 * @param prefix
	 * 
	 * @param joinPoint
	 */
	protected void doRequestLog(String prefix, JoinPoint joinPoint) {
		getLogger(joinPoint).info("{} 开始请求 {}, 请求参数: {}", prefix, joinPoint.getSignature().toShortString(),
				joinPoint.getArgs());
	}

	/**
	 * 在此方法中打印请求（方法）结果日志
	 * 
	 * @param prefix
	 * 
	 * @param joinPoint
	 * @param result
	 *            返回结果
	 */
	protected void doResponseLog(String prefix, JoinPoint joinPoint, Object result) {
		Logger logger = getLogger(joinPoint);
		if (logger.isDebugEnabled()) {
			logger.debug("{} 结束请求 {},  请求结果: {}", prefix, joinPoint.getSignature().toShortString(), result);
		} else {
			if (result instanceof NrcDataResult) {
				logger.info("{} 结束请求 {},  请求结果: {}", prefix, joinPoint.getSignature().toShortString(),
						((NrcDataResult<?>) result).toShortString());
			} else if (result instanceof PageData) {
				logger.info("{} 结束请求 {},  请求结果: {}", prefix, joinPoint.getSignature().toShortString(),
						((PageData<?>) result).toShortString());
			} else if (result instanceof List<?>) {
				List<?> data = (List<?>) result;
				logger.info("{} 结束请求 {},  请求结果: {}", prefix, joinPoint.getSignature().toShortString(),
						data == null ? null
								: data.size() == 0 ? "[]" : String.format("[%s, ...%s条记录]", data.get(0), data.size()));
			} else {
				logger.info("{} 结束请求 {},  请求结果: {}", prefix, joinPoint.getSignature().toShortString(),
						result == null ? null : StringUtils.abbreviate(result.toString(), 100));
			}
		}
	}

}
