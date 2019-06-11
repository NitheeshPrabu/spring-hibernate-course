package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.entity.Instructor;
import com.example.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

			// option 2. Query using HQL
			int id = 4;
			Query<Instructor> query = session.createQuery("select i from Instructor i "
															+ "join fetch i.courses "
															+ "where i.id=:theId", Instructor.class);

			// set query parameter
			query.setParameter("theId", id);

			// execute the query and get the instructor
			Instructor instructor = query.getSingleResult();

			// print instructor
			System.out.println("Instructor: " + instructor);

			System.out.println("Closing session...");
			session.close();

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
