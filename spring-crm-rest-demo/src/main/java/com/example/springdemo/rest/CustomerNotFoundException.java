package com.example.springdemo.rest;

public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(String s) {
		super(s);
	}

	public CustomerNotFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public CustomerNotFoundException(Throwable throwable) {
		super(throwable);
	}

	protected CustomerNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}

	public CustomerNotFoundException() {
		super();
	}
}
