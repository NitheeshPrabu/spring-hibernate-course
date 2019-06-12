package com.example.aopdemo;

import com.example.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

public class AroundWithLoggerDemoApp {

	private static Logger myLogger =
			Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

	public static void main(String[] args) {

		// read Spring config Java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		// get bean from the Spring container
		TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

		myLogger.info("\nMainProgram: AroundDemoApp");

		myLogger.info("Calling getFortune()");
		myLogger.info(fortuneService.getFortune());

		// close the context
		context.close();
	}
}
