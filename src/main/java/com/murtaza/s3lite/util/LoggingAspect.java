package com.murtaza.s3lite.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	public static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.murtaza.s3lite.service.*(..))" , throwing = "exception")
	public void logServiceException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
	
	@AfterThrowing(pointcut = "execution(* com.murtaza.s3lite.service.impl.*(..))" , throwing = "exception")
	public void logServiceExceptionImpl(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
	
}
