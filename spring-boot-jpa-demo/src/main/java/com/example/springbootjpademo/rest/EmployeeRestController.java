package com.example.springbootjpademo.rest;

import com.example.springbootjpademo.entity.Employee;
import com.example.springbootjpademo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		Employee employee = employeeService.getById(id);

		if (employee == null)
			throw new RuntimeException("Employee id not found - " + id);

		return employee;
	}

	@PostMapping("/employees/add")
	public Employee saveEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		employeeService.saveEmployee(employee);
		return employee;
	}

	@PutMapping("/employees/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return employee;
	}

	@DeleteMapping("/employees/delete/{id}")
	public String saveEmployee(@PathVariable int id) {
		Employee employee = employeeService.getById(id);

		if (employee == null)
			throw new RuntimeException("Employee id not found - " + id);

		employeeService.deleteById(id);
		return "Employee " + id + " is deleted.";
	}
}
