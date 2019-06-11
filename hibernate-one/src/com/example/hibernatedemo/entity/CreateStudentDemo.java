package com.example.hibernatedemo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		// create a session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// create a student object
			System.out.println("Creating new student object...");
			Student student = new Student("Paul", "Wall", "paul@example.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student object...");
			session.save(student);
		
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Commited the transaction!");
		} finally {
			sessionFactory.close();
		}
	}
}
