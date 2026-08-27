package com.shruthan.jobportalbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shruthan.jobportalbackend.model.Application;

public interface ApplicationRepository extends MongoRepository<Application, Integer>{

	
}
