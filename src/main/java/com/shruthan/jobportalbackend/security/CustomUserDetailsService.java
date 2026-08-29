package com.shruthan.jobportalbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserService service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return service.findUserByEmail(email);
	}

	
}
