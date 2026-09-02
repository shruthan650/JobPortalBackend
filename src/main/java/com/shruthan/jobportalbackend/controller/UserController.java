package com.shruthan.jobportalbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shruthan.jobportalbackend.model.User;
import com.shruthan.jobportalbackend.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		return service.addUser(user);
	}

	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUserById(@PathVariable String id) {
		return service.getUserById(id);
	}

	@PutMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void updateUserById(@RequestBody User user, @PathVariable String id) {
		service.updateUserById(user, id);
	}

	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteUserById(@PathVariable String id) {
		service.deleteUserById(id);
	}
}
