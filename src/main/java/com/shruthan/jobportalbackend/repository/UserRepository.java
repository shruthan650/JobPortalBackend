package com.shruthan.jobportalbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shruthan.jobportalbackend.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	

}
