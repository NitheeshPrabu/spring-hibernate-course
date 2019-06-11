package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForMaryDemo {

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

			// retrieve a student from DB
			int id = 2;
			Student student = session.get(Student.class, id);
			System.out.println("Retrieved student: " + student);
			System.out.println("Courses: " + student.getCourses());

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
