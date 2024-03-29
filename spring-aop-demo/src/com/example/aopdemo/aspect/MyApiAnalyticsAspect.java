package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

	@Before("com.example.aopdemo.aspect.AopExpression.forDaoPackageNoGetterSetter()")
	public void performApiAnalytics() {
		System.out.println("\n==========> Performing API Analytics");
		System.out.println();
	}
}