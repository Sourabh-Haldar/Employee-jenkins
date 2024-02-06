package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query
	List<Employee>	findByEmail(String email);
	@Query
	List<Employee>	findByFirstName(String firstName);
}
