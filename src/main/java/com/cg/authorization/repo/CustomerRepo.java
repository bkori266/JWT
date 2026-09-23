package com.cg.authorization.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.authorization.entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	
	//@Query("SELECT a from Customer a WHERE a.username=:username LIMIT 1")
	public Customer findByUsername(String username);

	public List<Customer> findByMobileNumber(String mobileNumber);

}
