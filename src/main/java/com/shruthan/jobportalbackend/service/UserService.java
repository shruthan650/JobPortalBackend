package com.shruthan.jobportalbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.config.PasswordConfig;
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
		return userRepository.findById(id).orElse(null);
	}

	public void updateUserById(User user, String id) {
		userRepository.save(user);
	}

	public void deleteUserById(String id) {
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
