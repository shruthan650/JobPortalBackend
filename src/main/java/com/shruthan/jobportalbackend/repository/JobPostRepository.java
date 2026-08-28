package com.shruthan.jobportalbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.shruthan.jobportalbackend.model.JobPost;

@Repository
public interface JobPostRepository extends MongoRepository<JobPost, String> {

}
