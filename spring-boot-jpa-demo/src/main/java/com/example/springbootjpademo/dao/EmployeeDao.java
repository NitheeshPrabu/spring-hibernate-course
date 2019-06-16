package com.example.springbootjpademo.dao;

import com.example.springbootjpademo.entity.Employee;
import java.util.List;

public interface EmployeeDao {

	public List<Employee> getEmployees();

	public Employee getById(int id);

	public void saveEmployee(Employee employee);

	public void deleteById(int id);
}
