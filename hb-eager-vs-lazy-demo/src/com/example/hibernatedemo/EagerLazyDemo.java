package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.entity.Instructor;
import com.example.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		// create a session
		Session session = sessionFactory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			int id = 4;
			Instructor instructor = session.get(Instructor.class, id);

			// print instructor
			System.out.println("Instructor: " + instructor);

			// breaks app because lazy loading is no longer possible
			System.out.println("Closing session...");
			session.close();

			// to fix and enable lazy loading when session is closed

			// option 1. call getter method before session is closed
			// i.e., just call getCourses() above line 34

			// option 2.

			// print associated courses - one-to-many mapping
			System.out.println("Associated courses: " + instructor.getCourses());

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Commited the transaction!");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {

			// to avoid leaking issue, do session.close()
			session.close();

			sessionFactory.close();
		}
	}
}
