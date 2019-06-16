package com.example.springbootjpademo.service;

import com.example.springbootjpademo.entity.Employee;

import java.util.List;

public interface EmployeeService {

	public List<Employee> getEmployees();

	public Employee getById(int id);

	public void saveEmployee(Employee employee);

	public void deleteById(int id);
}
