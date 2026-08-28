package com.shruthan.jobportalbackend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.model.JobPost;
import com.shruthan.jobportalbackend.repository.JobPostRepository;

@Service
public class JobPostService {
	
	@Autowired
	JobPostRepository jobPostRepository;

	public JobPost addJob(JobPost job) {
		
		if (job.getCreatedAt() == null) {
			job.setCreatedAt(LocalDateTime.now());
		}
		
		return jobPostRepository.save(job);
	}

	public List<JobPost> getAllJobs() {
		return jobPostRepository.findAll();
	}

	public JobPost getJobById(String id) {
		return jobPostRepository.findById(id).orElse(null);
	}

	public void updateJobById(String id, JobPost job) {
		jobPostRepository.save(job);
		
	}

	public void deleteJobById(String id) {
		jobPostRepository.deleteById(id);
		
	}

}
