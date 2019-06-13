package com.example.springdemo.dao;

import com.example.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

// @Repository always injected to DAO Implementations.
// Specifying this enables Spring to scan for a component that implements a certain DAO.
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// inject session factory using Dependency Injection
	// id is matched with variable name as specified in config file
	@Autowired
	private SessionFactory sessionFactory;

	// add @Transactional to automagically do session creation and closing
	// handled by Spring behind the scenes
	@Override
	//@Transactional
	public List<Customer> getCustomers() {

		// get current session created using @Transactional
		Session session = sessionFactory.getCurrentSession();

		// create the query, and return result sorted by last name
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

		// execute the query
		List<Customer> customers = query.getResultList();

		// return the results
		return customers;
	}

	@Override
	public void addCustomer(Customer customer) {

		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();

		// save the customer.. or update if record already exists
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {

		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();

		// return the customer
		return session.get(Customer.class, id);

	}

	@Override
	public void deleteCustomer(int id) {

		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();

		// delete customer based on primary key ID using HQL
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);

		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String name) {
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		Query query = null;

		// only search by name if name is not empty
		if (name != null && name.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			query = session.createQuery("from Customer where lower(firstName) like :customerName or lower(lastName) like :customerName", Customer.class);
			query.setParameter("customerName", name.toLowerCase() + "%");

		}
		else {
			// name is empty ... so just get all customers
			query = session.createQuery("from Customer order by lastName", Customer.class);
		}

		// execute query and get result list
		List<Customer> customers = query.getResultList();

		// return the results        
		return customers;
	}
}
