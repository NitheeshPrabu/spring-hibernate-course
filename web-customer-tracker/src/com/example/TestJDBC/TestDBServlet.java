package com.example.TestJDBC;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;

public class TestDBServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// setup connection variables
		String user = "springstudent";
		String password = "springstudent";

		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=FALSE";
		String driver = "com.mysql.jdbc.Driver";

		// get connection to database
		try {
			PrintWriter out = response.getWriter();
			out.println("Connecting to database: " + jdbcUrl);

			Class.forName(driver);

			Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

			out.println("Connected to database successfully!");

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}
}
