package com.cg.authorization.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.authorization.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
