package com.cg.authorization.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.cg.authorization.entity.Customer;
import com.cg.authorization.repo.CustomerRepo;
import com.cg.authorization.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo repo;
		
	BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	@Override
	public Customer save(Customer customer) {
		Customer repoCustomer=repo.findByUsername(customer.getUsername());
	
	
		if(repoCustomer==null) {
			List<Customer> listRepoCustomer=repo.findByMobileNumber(customer.getMobileNumber());
			if(listRepoCustomer.stream().count()==0) {
				customer.setPassword(encoder.encode(customer.getPassword()));
			return repo.save(customer);
			}
			else {
				throw new RuntimeException("Mobile number already used");
			}
			
				
		}
		
		else {
			throw new RuntimeException("------Customer ALready exist----");	
					
		}
		
		
	}

	@Override
	public List<Customer> getAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Customer> getById(Integer id) {
		return repo.findById(id);
	}
}
