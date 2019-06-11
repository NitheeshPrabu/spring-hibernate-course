package com.example.hibernatedemo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

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
			Student student = new Student("Daffy", "Duck", "daffyd@example.com");

			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student object...");
			System.out.println(student);
			session.save(student);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Commited the transaction!");

			// find out the student's id
			System.out.println("Saved student's id is: " + student.getId());

			// since commited, need to get a new session and start a transaction
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			// retrieve the student based on their id
			System.out.println("\nGetting student with id: " + student.getId());
			Student myStudent = session.get(Student.class, student.getId());

			System.out.println("Got the student: " + myStudent);

			// commit the transaction - have to commit even if SELECT query
			session.getTransaction().commit();
			System.out.println("Commited the transaction!");
		} finally {
			sessionFactory.close();
		}
	}
}
