package com.shruthan.jobportalbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.model.Company;
import com.shruthan.jobportalbackend.model.User;
import com.shruthan.jobportalbackend.repository.CompanyRepository;
import com.shruthan.jobportalbackend.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User addUser(User user) {
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

}
