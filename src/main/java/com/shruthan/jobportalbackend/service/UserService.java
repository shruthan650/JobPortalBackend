package com.shruthan.jobportalbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.config.PasswordConfig;
import com.shruthan.jobportalbackend.exception.InvalidInputException;
import com.shruthan.jobportalbackend.exception.ResourceNotFoundException;
import com.shruthan.jobportalbackend.model.User;
import com.shruthan.jobportalbackend.repository.UserRepository;
import com.shruthan.jobportalbackend.security.CustomUserDetails;

@Service
public class UserService {
	
	@Autowired
	private PasswordConfig passwordConfig;

	@Autowired
	UserRepository userRepository;
	
	CustomUserDetails customUserDetails;

	UserService(PasswordConfig passwordConfig) {
		this.passwordConfig = passwordConfig;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User addUser(User user) {
		
		user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User getUserById(String id) {
		User user = userRepository.findById(id).orElse(null);
		
		if (user == null) {
			throw new ResourceNotFoundException("User Not Found");
		}
		
		return user;
	}

	public void updateUserById(User user, String id) {
		
		if (!user.getId().equals(id)) {
			throw new InvalidInputException("User Id and input object Id doesn,t match");
		}
		
		if (userRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("User Not Found");
		}
		
		userRepository.save(user);
	}

	public void deleteUserById(String id) {
		
		if (userRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("User Not Found");
		}
		
		userRepository.deleteById(id);
	}
	
	public CustomUserDetails findUserByEmail(String email) {
		
		User requiredUser = userRepository.findByEmail(email);
		
		if (requiredUser != null) 
			return new CustomUserDetails(requiredUser);
		else 
			throw new UsernameNotFoundException("No email id with " + email + " is found");
	}

}
