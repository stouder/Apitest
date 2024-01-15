package com.apitest.commun;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	private long startTime;

	@Around("execution(* com.apitest.service.ReturnNumberService.getNumbers(..))")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		startTime = System.currentTimeMillis();
		log.info("Before executing: " + joinPoint.getSignature().toShortString() + " : " + sdf.format(new Date(startTime)));

		Object obj = joinPoint.proceed();

		long endTime = System.currentTimeMillis();
		log.info("After executing: " + joinPoint.getSignature().toShortString() + " : " + sdf.format(new Date(endTime)));
		log.info("Method execution time " + joinPoint.getSignature().toShortString() + " : " + (endTime - startTime)
				+ " milliseconds");
		
		return obj;

	}
}
