package com.example.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public void getSilly() {
		System.out.println(getClass() + ": Adding an account");
	}
}
