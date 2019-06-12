package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {

	public static void main(String[] args) {

		// read Spring config Java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		// get bean from the Spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

		// call the business method
		List<Account> accounts = accountDAO.findAccounts(false);

		// display the accounts
		System.out.println("\n\nMain Program: AfterReturningDemoApp");
		System.out.println(accounts);
		System.out.println();

		// close the context
		context.close();
	}
}
