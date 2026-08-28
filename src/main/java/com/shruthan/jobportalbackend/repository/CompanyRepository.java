package com.shruthan.jobportalbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shruthan.jobportalbackend.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String>{

	
}
