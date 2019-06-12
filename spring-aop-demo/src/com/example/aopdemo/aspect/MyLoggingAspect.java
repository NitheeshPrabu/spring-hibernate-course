package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

// to tell Spring its defining an Aspect
@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());

	/*
	 * This class defines a cross-cutting concern - logging
	 * Each aspect has an advice type, a joinpoint and a pointcut
	 *
	 * Advice type tells when the Aspect should execute, eg. before, after (done executing),
	 * after return, after throw, around, etc.
	 *
	 * Join Point: A joinpoint is a candidate point in the Program Execution of the application where an aspect can be plugged in.
	 * Spring only supports method execution. AspectJ offers all join point support - method call,
	 * exception throw and field modification
	 *
	 * Point Cut: A pointcut defines at what joinpoints, the associated Advice should be applied
	 * Use Pointcut expression language to define predicates that match and specify when to execute
	 *          execution(modifiers-pattern? return-type-pattern declaring-type-pattern?
	 *                      method-name-pattern(param-pattern) throws-pattern?)
	 * Patterns can also use wildcards, eg. * (matches everything)
	 *
	 * Spring supports run-time weaving, which has minor performance hit.
	 * AspectJ does not support run-time, but supports compile-time, post-compile and load-time
	 * weaving
	 *
	*/

	@Before("com.example.aopdemo.aspect.AopExpression.forDaoPackageNoGetterSetter()")
	// Having JoinPoint argument helps intercept the method signature that is used when calling
	// the method for which the aspect is plugged in
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {
		myLogger.info("\n==========> Executing @Before Advice on addAccount()");

		// displaying method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		myLogger.info("Method: " + methodSig);

		// displaying method arguments
		// need to get the arguments and loop through them
		Object[] args = joinPoint.getArgs();
		for (Object arg: args) {
			myLogger.info(arg.toString());

			if (arg instanceof Account) {
				myLogger.info("Account Name: " + ((Account) arg).getName());
				myLogger.info("Account Level: " + ((Account) arg).getLevel());
			}
		}

		myLogger.info("");
	}

	// @AfterReturning: Executed when method is done and before control is transferred to the calling method
	// Uses: logging, security, transactions, post-processing data (enrich data, format to meet requirements etc..)
	@AfterReturning(
			pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result"
	)
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n=======> Executing @AfterReturning on method: " + method);
		myLogger.info("\n=======> Result is: " + result);

		// post-processing data
		// any changes made to result will be reflected to the calling method's returning variables
		modifyContent(result);

	}

	//@AfterThrowing: Executed when method throws an exception
	//Uses: logging the exception, performing audits on exception, notify devops team
	@AfterThrowing(
			pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "exception"
	)
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n=======> Executing @AfterThrowing on method: " + method);
		myLogger.info("\n=======> Exception is: " + exception);
	}

	private void modifyContent(List<Account> result) {

		for (Account account: result) {
			account.setName(account.getName().toUpperCase() + " MODIFIED");
		}
	}

	// @After: Executed finally, whether methods completes execution successfully or fails.
	// Does not have access to the exception if any is thrown. This is because the @After runs
	// BEFORE the @AfterThrowing advice
	// Uses: Logging, auditing
	@After("execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n=======> Executing @After on method: " + method);
	}

	// @Around: Executed before and after method execution. Just like combining @Before and @After,
	// but more fine-grained control
	// Uses: logging, auditing, security, pre and post-processing data,
	// instrumentation/profiling code, managing exceptions (swallow, handle, stop)
	// You will get a reference to a "proceeding joinpoint", a handle to the target method.
	// We can use the proceeding joinpoint to execute the target method
	@Around("execution(* com.example.aopdemo.service.TrafficFortuneService.getFortune(..))")
	public Object aroundTrafficFortuneServiceAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		String method = proceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=======> Executing @Around on method: " + method);

		long begin = System.currentTimeMillis();

		Object result = null;
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			myLogger.warning(e.getMessage());

			// give custom message if deciding to handle exception
			result = "Major accident! But no worries, your private helicopter " +
					"is on it's way!";
			// otherwise rethrow
			//throw e;
		}

		long end = System.currentTimeMillis();

		myLogger.info("\n=======> Execution took " + (end-begin)/1000.0 + " seconds");

		return result;
	}
}
