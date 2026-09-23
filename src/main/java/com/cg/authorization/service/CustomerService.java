package com.cg.authorization.service;

import java.util.List;
import java.util.Optional;

import com.cg.authorization.entity.Customer;

public interface CustomerService {
	
	Customer save(Customer customer);
	
	List<Customer> getAll();
	
	Optional<Customer> getById(Integer id);
	

}
