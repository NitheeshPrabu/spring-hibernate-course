package com.example.springbootdatajpademo.service;

import com.example.springbootdatajpademo.dao.EmployeeRepository;
import com.example.springbootdatajpademo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// no need @Transactional because using JpaRepository
	// provides standardised API interface for CRUD operations (and others)
	// Declare a new interface extending JpaRepository, and passing reference to the
	// entity going to map and the associated primary key
	private EmployeeRepository employeeRepo;

	public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}


	@Override
	public Employee getById(int id) {

		// introduced in Java. To be used where searching can result nothing
		Optional<Employee> result = employeeRepo.findById(id);

		Employee employee = null;
		if (!result.isPresent())
			throw new RuntimeException("Could not find employee id - " + id);

		employee = result.get();

		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepo.save(employee);
	}

	@Override
	public void deleteById(int id) {
		employeeRepo.deleteById(id);
	}
}
