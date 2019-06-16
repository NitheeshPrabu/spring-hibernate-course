package com.example.springboothibernatedemo.dao;

import com.example.springboothibernatedemo.entity.Employee;
import java.util.List;

public interface EmployeeDao {

	public List<Employee> getEmployees();

	public Employee getById(int id);

	public void saveEmployee(Employee employee);

	public void deleteById(int id);
}
