package com.dnt.cloud.logger;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggerAspect4Controller extends LoggerAspect {

	@Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
	public void loggerAspect() {
	}

	@Override
	protected void doRequestLog(String prefix, JoinPoint joinPoint) {
		ServletRequestAttributes reqSttr = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
		if (reqSttr != null) {
			HttpServletRequest request = reqSttr.getRequest();
			String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
			String contentType = request.getContentType() == null ? "" : ", " + request.getContentType();
			getLogger(joinPoint).info("{} Request from {}, {}{}, {}{}", prefix, request.getRemoteHost(),
					request.getMethod(), contentType, request.getRequestURL().toString(), queryString);
		}
		super.doRequestLog(prefix, joinPoint);
	}

}
