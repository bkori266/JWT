package com.cg.authorization.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.authorization.entity.Employee;
import com.cg.authorization.repo.EmployeeRepo;
import com.cg.authorization.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo repo;
	
	BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	@Override
	public Employee save(Employee emp) {
		emp.setPassword(encoder.encode(emp.getPassword()));
		return repo.save(emp);
	}

	@Override
	public List<Employee> getAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Employee> getById(Integer id) {
		return repo.findById(id);
	}
}
