package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.Instructor;
import com.example.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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
			int id = 3;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

			// print instructor detail
			System.out.println("Found InstructorDetail: " + instructorDetail);

			// print associated instructor - bidirectional mapping
			System.out.println("Associated instructor: " + instructorDetail.getInstructor());

			// delete the instructor detail
			// also cascase deletes associated instructor

			// once modified to remove only the instructor detail, we will get error because of
			// previous associations set up by the bidirectional link. we need to break the link between
			// instructor and instructor detail

			// breaks Instructor ---> InstructorDetail link
			instructorDetail.getInstructor().setInstructorDetail(null);


			session.delete(instructorDetail);

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
