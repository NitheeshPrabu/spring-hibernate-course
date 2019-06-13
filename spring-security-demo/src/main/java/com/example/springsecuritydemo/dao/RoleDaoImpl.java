package com.example.springsecuritydemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springsecuritydemo.entity.Role;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role findRoleByName(String roleName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", roleName);
		
		Role role = null;
		
		try {
			role = theQuery.getSingleResult();
		} catch (Exception e) {
			role = null;
		}
		
		return role;
	}

	@Override
	public List<Role> getAllRoles() {
		// get current session created using @Transactional
		Session session = sessionFactory.getCurrentSession();

		// create the query, and return result sorted by last name
		Query<Role> query = session.createQuery("from Role", Role.class);

		// execute the query
		List<Role> roles = query.getResultList();

		// return the results
		return roles;
	}
}
