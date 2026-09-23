package com.cg.authorization.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cg.authorization.entity.Customer;
import com.cg.authorization.entity.CustomerPrincipal;
import com.cg.authorization.repo.CustomerRepo;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	CustomerRepo repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer=repo.findByUsername(username);
		if(customer==null) {
			throw new RuntimeException("No Customer found");
		}
		
		return new CustomerPrincipal(customer);
	}

}
