package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.Instructor;
import com.example.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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

			// start a transaction
			session.beginTransaction();

			// get instructor by primary key
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);
			System.out.println("Found instructor: " + instructor);

			// delete the instructor
			if (instructor != null) {
				System.out.println("Deleting: " + instructor);

				// also deletes InstructorDetail object due to CascadeType.ALL
				session.delete(instructor);
			}

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Commited the transaction!");
		} finally {
			sessionFactory.close();
		}
	}
}
