package com.cg.authorization.service;

import java.util.List;
import java.util.Optional;

import com.cg.authorization.entity.Employee;

public interface EmployeeService {
	
	Employee save(Employee emp);
	
	List<Employee> getAll();
	
	Optional<Employee> getById(Integer id);
	

}
