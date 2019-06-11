package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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

			// create a course
			Course course = new Course("Pacman - How to score one million points");

			// save course
			System.out.println("Saving course: " + course);
			session.save(course);

			// create students
			Student student1 = new Student("John", "Doe", "jdoe@microsoft.com");
			Student student2 = new Student("Mary", "Public", "maryp@bing.com");

			// add students to the course
			course.addStudent(student1);
			course.addStudent(student2);

			// save the students, cascading saves courses too
			System.out.println("Saving student: " + student1);
			session.save(student1);

			System.out.println("Saving student: " + student2);
			session.save(student2);

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
