package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.Instructor;
import com.example.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		// create a session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// create the objects
			Instructor instructor = new Instructor("John", "Doe", "jdoe@google.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://youtube.com/jDOE", "coding");

			// associate the objects
			instructor.setInstructorDetail(instructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor object
		    // NOTE: this will also save the InstructorDetails object because of CascadeType.ALL
			System.out.println("Saving instructor: " + instructor);
			session.save(instructor);
		
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Commited the transaction!");
		} finally {
			sessionFactory.close();
		}
	}
}
