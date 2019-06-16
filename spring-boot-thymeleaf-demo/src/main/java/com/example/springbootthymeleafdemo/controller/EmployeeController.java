package com.example.springbootthymeleafdemo.controller;

import com.example.springbootthymeleafdemo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private List<Employee> employees;

	@PostConstruct
	public void loadEmployees() {
		Employee e1 = new Employee(1, "John", "Doe", "john@example.com");
		Employee e2 = new Employee(2, "Emma", "Baumgarten", "emmab@example.com");
		Employee e3 = new Employee(3, "Avani", "Gupta", "agupta@example.com");

		employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
	}

	@GetMapping("/list")
	public String listEmployees(Model model) {
		model.addAttribute("employees", employees);

		return "list-employees";
	}
}
