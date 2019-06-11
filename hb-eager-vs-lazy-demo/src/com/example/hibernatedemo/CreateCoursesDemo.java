package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.entity.Instructor;
import com.example.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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

			// create the course object
			Course course1 = new Course("Poker Bluffs");
			Course course2 = new Course("Piano Masterclass");

			// start a transaction
			session.beginTransaction();

			// get instructor from DB
			int id = 4;
			Instructor instructor = session.get(Instructor.class, id);

			// add courses to instructor
			instructor.add(course1);
			instructor.add(course2);

			// save the courses
			System.out.println("Saving course: " + course1);
			session.save(course1);

			System.out.println("Saving course: " + course2);
			session.save(course2);

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
