package com.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.login.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig 
{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		
		http.csrf().disable()
//		.cors()
//		.disable()
//		.authorizeRequests()
		.authorizeHttpRequests()
		.requestMatchers("/token").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		return null;
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	
	
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(this.customUserDetailsService);
	}
	
	
	  @Bean
	    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception
	    {
	    	
	    	return configuration.getAuthenticationManager();
	    }
	    
}
