package com.example.hibernatedemo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		// create a session
		Session session = sessionFactory.getCurrentSession();
		
		try {

			// start a transaction
			session.beginTransaction();
			
			// query the student table
			// use the case and variable names as per the Java model class, not the SQL table.
			List<Student> studentList = session.createQuery("from Student").getResultList();

			// display the result set
			System.out.println("\nList of all students:\n");
			displayStudents(studentList);

			studentList = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

			// display the result set
			System.out.println("\nList of students with last_name = Doe");
			displayStudents(studentList);

			/* Update queries
			*
			* Using setters
			* Student myStudent = session.get(Student.class, studentId);
			* myStudent.setFirstName(newName);
			* session.getTransaction().commit();
			*
			* Using HQL query
			* session.createQuery("update Student set email='new email'").executeUpdate();
			*/

			/* Delete queries
			*
			* Using the session object
			* session.delete(myStudent)
			*
			* Using HQL query
			* session.createQuery("delete from Student where id=studentId").executeUpdate();
			*/

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Commited the transaction!");
		} finally {
			sessionFactory.close();
		}
	}

	private static void displayStudents(List<Student> studentList) {
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
}
