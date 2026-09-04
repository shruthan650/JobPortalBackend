package com.shruthan.jobportalbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shruthan.jobportalbackend.security.CustomUserDetailsService;
import com.shruthan.jobportalbackend.security.JWTFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	JWTFilter jwtFilter;
	
	@Autowired
	PasswordConfig passwordConfig;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {

		return http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers(
								"/api/greet",
								"/api/login",
								"/api/jobs"
						)
						.permitAll()
						
						.requestMatchers(
								HttpMethod.POST,
								"/api/user"
								)
						.permitAll()
						
						.anyRequest()
						.authenticated()
				)
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
		
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
