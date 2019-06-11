package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.Course;
import com.example.hibernatedemo.entity.Instructor;
import com.example.hibernatedemo.entity.InstructorDetail;
import com.example.hibernatedemo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.buildSessionFactory();
		// create a session
		Session session = sessionFactory.getCurrentSession();
		
		try {

			// start a transaction
			session.beginTransaction();

			// create a course
			Course course = new Course("Pacman - How to score one million points");

			// add some reviews
			course.addReview(new Review("I learnt a lot from this course!"));
			course.addReview(new Review("Well explained with actual proof. Job well done"));
			course.addReview(new Review("Now I'm the King of Pacman thanks to this course!"));
			course.addReview(new Review("We have courses for Pacman now?! What has the world come to??"));

			// save course, cascade all saves reviews too
			System.out.println("Saving course: " + course);
			System.out.println("Reviews: " + course.getReviews());
			session.save(course);

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
