package com.cg.authorization.controller;

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

import com.cg.authorization.entity.Customer;
import com.cg.authorization.service.CustomerService;
import com.cg.authorization.serviceImpl.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
		
	@GetMapping("/getAll")
	public List<Customer> getAll(){ 
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Customer> getById(@PathVariable Integer id){ 
		return service.getById(id);
	}
	
	@PostMapping("/register")
	public Customer addEmployeeCustomer(@RequestBody Customer customer) {
		return service.save(customer);
	}
	
	@PostMapping("/login")  
	public String login(@RequestBody Customer customer) {
		Authentication auth=authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword()));
		if(auth.isAuthenticated())
			return jwtService.generateToken(customer.getUsername());
		else
		return "Failure to genrate token";
	
	}
	
	@GetMapping("token")
	public CsrfToken token(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	@GetMapping("/hello")
	public String hello(HttpServletRequest request) {  
		return "Hello: "+request.getRequestedSessionId(); 
	}
	
	

}
