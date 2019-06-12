package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpression {

	// match on addAccount() method with no arguments from ANY class
	//@Before("execution(public void addAccount())")

	// match on any method with no arguments that starts with "add" from any class
	//@Before("execution(public void add*())")

	// match on addAccount() method with 1 argument (any type) from any class
	//@Before("execution(public void addAccount(*))")

	// match on match on any method with no arguments that starts with "add"
	// with 0 or more arguments (any type) from any class
	//@Before("execution(* add*(..))")

	/*
	 * Pointcut declarations - useful for pointcuts that are reused multiple times
	 * Also used for combining pointcut expressions - using expression logic
	 * All involved expressions must resolve to a true for the advice to be applied
	 */

	// matches everything inside com.example.aopdemo.dao package
	@Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {}

	// matches all getter methods inside com.example.aopdemo.dao package
	@Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
	public void forDaoPackageGetters() {}

	// matches all setters methods inside com.example.aopdemo.dao package
	@Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
	public void forDaoPackageSetters() {}

	// combine pointcuts, all methods except getters and setters
	@Pointcut("forDaoPackage() && !(forDaoPackageGetters() || forDaoPackageSetters())")
	public void forDaoPackageNoGetterSetter() {}
}
