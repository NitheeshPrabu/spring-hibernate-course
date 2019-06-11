package com.example.hibernatedemo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create a session
        Session session = sessionFactory.getCurrentSession();

        try {
            // create 3 student objects
            System.out.println("Creating new student object...");
            Student student1 = new Student("Chuck", "Norris", "chuckn@example.com");
            Student student2 = new Student("John", "Doe", "john.doe@example.com");
            Student student3 = new Student("Mary", "Bonita", "bmary@example.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student objects...");
            session.save(student1);
            session.save(student2);
            session.save(student3);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Commited the transaction!");
        } finally {
            sessionFactory.close();
        }
    }
}
