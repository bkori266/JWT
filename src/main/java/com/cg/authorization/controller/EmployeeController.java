package com.cg.authorization.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.authorization.entity.Employee;
import com.cg.authorization.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
		
	@GetMapping("/getAll")
	public List<Employee> getAll(){ 
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Employee> getById(@PathVariable Integer id){ 
		return service.getById(id);
	}
	
	@PostMapping("/add")  
	public Employee addEmployee(@RequestBody Employee employee) {
		return service.save(employee);
	}
	
	@GetMapping("token")
	public CsrfToken token(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	@GetMapping("/hello")
	public String hello(HttpServletRequest request) {  return "Hello: "+request.getRequestedSessionId(); }
	
	

}
