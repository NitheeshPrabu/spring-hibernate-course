package com.example.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup the logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// setup pointcut declarations
	@Pointcut("execution(* com.example.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}

	@Pointcut("execution(* com.example.springdemo.service.*.*(..))")
	private void forServicePackage() {}

	@Pointcut("execution(* com.example.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}

	// match logging for all methods in all classes of controller, service and doa packages
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}

	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {

		String method = joinPoint.getSignature().toShortString();
		myLogger.info("====> in @Before: calling method: " + method);

		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			myLogger.info("====> args: " + arg);
		}
	}

	// add @AfterReturning advice
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "result"
	)
	public void afterReturning(JoinPoint joinPoint, Object result) {

		String method = joinPoint.getSignature().toShortString();
		myLogger.info("====> in @AfterReturning: from method: " + method);

		myLogger.info("====> returning the result: " + result);
	}
}
