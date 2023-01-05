package com.dnt.cloud.logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect4Service extends LoggerAspect {

	@Pointcut("@within(org.springframework.stereotype.Service)")
	public void loggerAspect() {
	}

}
