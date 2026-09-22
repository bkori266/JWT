package com.cg.authorization.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.authorization.entity.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	
	//@Query("SELECT a from Employee a WHERE a.username=:username")
	public Employee findByUsername(String username);

}
