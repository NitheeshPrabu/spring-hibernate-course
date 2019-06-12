package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)       // Used to give priority - range: INT_MIN - max priority, INT_MAX - min priority
public class MyCloudLoggingAspect {

	@Before("com.example.aopdemo.aspect.AopExpression.forDaoPackageNoGetterSetter()")
	public void logToCloudAsync() {
		System.out.println("\n==========> Performing logging to Cloud");
		System.out.println();
	}
}
