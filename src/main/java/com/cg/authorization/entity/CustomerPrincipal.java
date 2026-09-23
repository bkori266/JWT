package com.cg.authorization.entity;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public class CustomerPrincipal implements UserDetails {

	
	private Customer customer;
	
	public CustomerPrincipal(Customer customer) {
		this.customer=customer;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public @Nullable String getPassword() {
		
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
	
		return customer.getUsername();
	}

}
