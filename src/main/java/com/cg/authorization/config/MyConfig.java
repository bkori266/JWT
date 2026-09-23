package com.cg.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MyConfig {
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JWTFilter JWTFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(custom->custom.disable());
		httpSecurity.authorizeHttpRequests(request->request
									.requestMatchers("/auth/register","/auth/login")
									.permitAll()
									.anyRequest().authenticated());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.addFilterBefore(JWTFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		
		return provider;
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
		return configuration.getAuthenticationManager();
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

















