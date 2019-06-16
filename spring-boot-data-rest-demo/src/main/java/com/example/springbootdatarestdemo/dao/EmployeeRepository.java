package com.example.springbootdatarestdemo.dao;

import com.example.springbootdatarestdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// by default gives endpoint pointing to entityname followed by 's'.
@RepositoryRestResource(path="members") // now REST endpoint points to /base-path/members
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
