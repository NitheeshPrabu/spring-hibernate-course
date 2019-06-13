package com.example.springdemo.rest;

import com.example.springdemo.entity.Student;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class StudentRestController {

	private List<Student> students;

	// called only once when the Controller is constructed
	@PostConstruct
	public void loadData() {

		students = new ArrayList<>();
		students.add(new Student("Parvati", "Patel"));
		students.add(new Student("Dean", "Thomas"));
		students.add(new Student("Luna", "Lovegood"));
	}

	@GetMapping("/students")
	public List<Student> getAllStudents() {

		return students;
	}

	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable int id) {

		if (id >= students.size() || id < 0)
			throw new StudentNotFoundException("Invalid ID " + id);

		return students.get(id);
	}
}
