package com.shruthan.jobportalbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.shruthan.jobportalbackend.model.JobPost;

@Component
public interface JobPostRepository extends MongoRepository<JobPost, String> {

}
