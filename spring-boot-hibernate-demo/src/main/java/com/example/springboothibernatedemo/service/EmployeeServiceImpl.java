package com.example.springboothibernatedemo.service;

import com.example.springboothibernatedemo.dao.EmployeeDao;
import com.example.springboothibernatedemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;

	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeDao.getEmployees();
	}


	@Override
	@Transactional
	public Employee getById(int id) {
		return employeeDao.getById(id);
	}

	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		employeeDao.saveEmployee(employee);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		employeeDao.deleteById(id);
	}
}
