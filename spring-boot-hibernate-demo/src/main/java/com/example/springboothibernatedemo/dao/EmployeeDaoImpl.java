package com.example.springboothibernatedemo.dao;

import com.example.springboothibernatedemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	// define the field for EntityManager
	private EntityManager entityManager;

	// set up constructor injection
	// @Autowired not necessary if only one item is injected
	@Autowired
	public EmployeeDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> getEmployees() {
		Session session = entityManager.unwrap(Session.class);
		Query<Employee> query = session.createQuery("from Employee", Employee.class);
		List<Employee> employees = query.getResultList();
		return employees;
	}

	@Override
	public Employee getById(int id) {
		Session session = entityManager.unwrap(Session.class);

		Employee employee = session.get(Employee.class, id);
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createQuery("delete from Employee where id=:theId");
		query.setParameter("theId", id);

		query.executeUpdate();
	}
}
