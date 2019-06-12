package com.example.aopdemo.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TrafficFortuneService {

	public String getFortune() {

		try {
			// sleeps for 5 seconds to simulate delay
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Expect HEAVY traffic this morning.. :(";
	}

	public String getFortune(boolean throwException) {
		if (throwException) {
			throw new RuntimeException("Major Accident! Highway closed!");
		}

		return getFortune();
	}
}
