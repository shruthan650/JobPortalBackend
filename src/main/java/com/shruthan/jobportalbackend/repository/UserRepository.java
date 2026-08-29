package com.shruthan.jobportalbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shruthan.jobportalbackend.model.User;
import java.util.List;


public interface UserRepository extends MongoRepository<User, String>{
	

	public User findByEmail(String email);
}
