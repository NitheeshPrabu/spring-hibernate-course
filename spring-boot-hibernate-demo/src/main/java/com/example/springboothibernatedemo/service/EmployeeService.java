package com.example.springboothibernatedemo.service;

import com.example.springboothibernatedemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

	public List<Employee> getEmployees();

	public Employee getById(int id);

	public void saveEmployee(Employee employee);

	public void deleteById(int id);
}
