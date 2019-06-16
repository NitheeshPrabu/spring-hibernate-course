package com.example.springbootjpademo.dao;

import com.example.springbootjpademo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> getEmployees() {
		Query query = entityManager.createQuery("from Employee");

		List<Employee> employees = query.getResultList();
		return employees;
	}

	@Override
	public Employee getById(int id) {
		return entityManager.find(Employee.class, id);
	}

	@Override
	public void saveEmployee(Employee employee) {
		Employee dbEmployee = entityManager.merge(employee);

		employee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int id) {
		Query query = entityManager.createQuery("delete from Employee where id=:theId");
		query.setParameter("theId", id);
		query.executeUpdate();
	}
}
