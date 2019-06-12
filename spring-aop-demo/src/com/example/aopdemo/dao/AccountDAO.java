package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

	public void addAccount(Account account, boolean isVIP) {
		System.out.println(getClass() + ": Adding an account");
	}

	public List<Account> findAccounts(boolean throwException) {

		if (throwException) {
			throw new RuntimeException("OOPS! EXCEPTION! EXCEPTION!");
		}

		List<Account> accounts = new ArrayList<>();

		// create sample accounts and add to the result list
		accounts.add(new Account("John", "Silver"));
		accounts.add(new Account("Madhu", "Platinum"));
		accounts.add(new Account("Luca", "Gold"));

		return accounts;
	}
}
