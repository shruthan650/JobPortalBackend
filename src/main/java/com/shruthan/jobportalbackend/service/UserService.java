package com.shruthan.jobportalbackend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	CustomUserDetails customUserDetails;

	UserService(PasswordConfig passwordConfig) {
		this.passwordConfig = passwordConfig;
	}

	public List<User> getAllUsers() {
		logger.info("All users fetched");
		return userRepository.findAll();
	}

	public User addUser(User user) {
		
		user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User getUserById(String id) {
		User user = userRepository.findById(id).orElse(null);
		
		if (user == null) {
			logger.debug("ResourceNotFoundException thrown from getUserById service");
			throw new ResourceNotFoundException("User Not Found");
		}
		
		return user;
	}

	public void updateUserById(User user, String id) {
		
		if (!user.getId().equals(id)) {
			logger.debug("InvalidInputException thrown from updateUserById service");
			throw new InvalidInputException("User Id and input object Id doesn't match");
		}
		
		if (userRepository.findById(id).isEmpty()) {
			logger.debug("ResourceNotFoundException thrown from updateUserById service");
			throw new ResourceNotFoundException("User Not Found");
		}
		
		userRepository.save(user);
	}

	public void deleteUserById(String id) {
		
		if (userRepository.findById(id).isEmpty()) {
			logger.debug("ResourceNotFoundException thrown from deleteUserById service");
			throw new ResourceNotFoundException("User Not Found");
		}
		
		userRepository.deleteById(id);
	}
	
	public CustomUserDetails findUserByEmail(String email) {
		
		User requiredUser = userRepository.findByEmail(email);
		
		if (requiredUser != null) 
			return new CustomUserDetails(requiredUser);
		else {
			logger.debug("UserNameNotFoundException thrown from findUserByEmail service");
			throw new UsernameNotFoundException("No email id with " + email + " is found");
			
		}
	}

}
