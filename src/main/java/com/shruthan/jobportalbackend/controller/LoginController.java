package com.shruthan.jobportalbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shruthan.jobportalbackend.model.User;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public Authentication getCredentials(@RequestBody User user) {
		
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
				);
	
	}
}
