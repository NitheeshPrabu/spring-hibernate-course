package com.example.aopdemo;

import com.example.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {

	private static Logger myLogger =
			Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

	public static void main(String[] args) {

		// read Spring config Java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		// get bean from the Spring container
		TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

		myLogger.info("\nMainProgram: AroundHandleExceptionDemoApp");

		myLogger.info("Calling getFortune()");

		// Exception does not reach the Main Program, handled inside @Around advice
		myLogger.info(fortuneService.getFortune(true));

		// close the context
		context.close();
	}
}
