package com.cg.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(custom->custom.disable());
		httpSecurity.authorizeHttpRequests(request->request.anyRequest().authenticated());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return httpSecurity.build();
	}

	// This bean help for Username password authneticate
	@Bean
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
	
	
	
}

















