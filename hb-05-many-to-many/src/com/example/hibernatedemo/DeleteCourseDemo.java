package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		// create a session
		Session session = sessionFactory.getCurrentSession();
		
		try {

			// start a transaction
			session.beginTransaction();

			// retrieve a course
			int id = 10;
			Course course = session.get(Course.class, id);

			// delete the course
			System.out.println("Deleting course: " + course);
			session.delete(course);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Commited the transaction!");
		} catch (Exception e)  {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
