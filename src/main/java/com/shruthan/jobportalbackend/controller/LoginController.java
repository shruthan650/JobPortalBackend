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
import com.shruthan.jobportalbackend.security.JWTService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JWTService jwtService;

	@PostMapping("/login")
	public String getCredentials(@RequestBody User user) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

		return jwtService.generateToken(authentication.getName());

	}
}
