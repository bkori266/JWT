package com.cg.authorization.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cg.authorization.entity.Employee;
import com.cg.authorization.repo.EmployeeRepo;
import com.cg.authorization.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo repo;
	
	@Override
	public Employee save(Employee emp) {
		// TODO Auto-generated method stub
		return repo.save(emp);
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<Employee> getById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

}
