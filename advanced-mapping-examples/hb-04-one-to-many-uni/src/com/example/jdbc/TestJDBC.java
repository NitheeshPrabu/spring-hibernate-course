package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-04-one-to-many-uni?useSSL=false";
		String username = "hbstudent";
		String password = "hbstudent";
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection con = DriverManager.getConnection(jdbcUrl, username, password);
			
			System.out.println("Connection successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
