package com.example.springsecuritydemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springsecuritydemo.entity.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String userName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> query = currentSession.createQuery("from User where userName=:uName", User.class);
		query.setParameter("uName", userName);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}

		return user;
	}

	@Override
	public void save(User user) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create the user
		currentSession.saveOrUpdate(user);
	}

	@Override
	public List<User> getAllUsers() {
		// get current session created using @Transactional
		Session session = sessionFactory.getCurrentSession();

		// create the query, and return result sorted by last name
		Query<User> query = session.createQuery("from User order by lastName", User.class);

		// execute the query
		List<User> users = query.getResultList();

		// return the results
		return users;
	}
}
