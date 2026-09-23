package com.cg.authorization.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.authorization.entity.Employee;
import com.cg.authorization.service.EmployeeService;
import com.cg.authorization.serviceImpl.JwtService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
		
	@GetMapping("/getAll")
	public List<Employee> getAll(){ 
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Employee> getById(@PathVariable Integer id){ 
		return service.getById(id);
	}
	
	@PostMapping("/register")
	public Employee addEmployee(@RequestBody Employee employee) {
		return service.save(employee);
	}
	
	@PostMapping("/login")  
	public String login(@RequestBody Employee emp) {
		Authentication auth=authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(emp.getUsername(), emp.getPassword()));
		if(auth.isAuthenticated())
			return jwtService.generateToken(emp.getUsername());
		else
		return "Failure";
	
	}
	
	@GetMapping("token")
	public CsrfToken token(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	@GetMapping("/hello")
	public String hello(HttpServletRequest request) {  return "Hello: "+request.getRequestedSessionId(); }
	
	

}
