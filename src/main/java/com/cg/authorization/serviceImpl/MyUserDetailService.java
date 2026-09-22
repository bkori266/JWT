package com.cg.authorization.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.authorization.entity.Employee;
import com.cg.authorization.entity.EmployeePrincipal;
import com.cg.authorization.repo.EmployeeRepo;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	EmployeeRepo repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee=repo.findByUsername(username);
		if(employee==null) {
			throw new RuntimeException("No Employee found");
		}
		
		System.out.println(employee);
		return new EmployeePrincipal(employee);
	}

}
