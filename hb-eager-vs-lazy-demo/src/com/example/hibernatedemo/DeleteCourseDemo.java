package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.entity.Instructor;
import com.example.hibernatedemo.entity.InstructorDetail;
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
										.buildSessionFactory();
		// create a session
		Session session = sessionFactory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// get a course
			int id = 10;
			Course course = session.get(Course.class, id);

			// delete the course
			System.out.println("Deleting the course: " + course);
			session.delete(course);

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
