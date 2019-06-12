package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {

		// read Spring config Java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		// get bean from the Spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

		// call the business method
		List<Account> accounts = null;
		try {
			accounts = accountDAO.findAccounts(true);
		} catch (Exception e) {
			System.out.println("Caught exception: " + e);
		}

		// display the accounts
		System.out.println("\n\nMain Program: AfterThrowingDemoApp");
		System.out.println(accounts);
		System.out.println();

		// close the context
		context.close();
	}
}
