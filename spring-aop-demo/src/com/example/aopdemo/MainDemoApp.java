package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

	public static void main(String[] args) {

		// read Spring config Java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		// get bean from the Spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

		Account account = new Account();

		// these will not get logged, as no joinpoints defined for getters/setters
		account.setName("Mary");
		account.setLevel("Platinum");

		// call the business method
		accountDAO.addAccount(account, true);

		// get bean from the Spring container
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

		// call the business method
		membershipDAO.getSilly();

		// close the context
		context.close();
	}
}
