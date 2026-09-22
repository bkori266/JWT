package com.cg.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(custom->custom.disable());
		httpSecurity.authorizeHttpRequests(request->request.anyRequest().authenticated());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return httpSecurity.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		
		return provider;
	}

	// This bean help for Username password authneticate with static name and password
/*	@Bean
	public UserDetailsService userDetailService() {
		UserDetails user=User.withDefaultPasswordEncoder()
							.username("aaa")
							.password("aaa")
							.roles("User")
							.build();
		
		UserDetails admin=User.withDefaultPasswordEncoder()
				.username("bbb")
				.password("bbb")
				.roles("Admin")
				.build();
		
		
		return new InMemoryUserDetailsManager(user,admin);
		
	}
	
	*/
	
}

















