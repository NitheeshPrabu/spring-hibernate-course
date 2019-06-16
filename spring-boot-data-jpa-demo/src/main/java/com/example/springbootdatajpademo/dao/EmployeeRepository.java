package com.example.springbootdatajpademo.dao;

import com.example.springbootdatajpademo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
