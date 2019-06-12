package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AroundDemoApp {

	public static void main(String[] args) {

		// read Spring config Java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		// get bean from the Spring container
		TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

		System.out.println("\nMainProgram: AroundDemoApp");

		System.out.println("Calling getFortune()");
		System.out.println(fortuneService.getFortune());

		// close the context
		context.close();
	}
}
