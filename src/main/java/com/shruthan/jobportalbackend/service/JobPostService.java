package com.shruthan.jobportalbackend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.exception.InvalidInputException;
import com.shruthan.jobportalbackend.exception.ResourceNotFoundException;
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
		
		JobPost jobPost = jobPostRepository.findById(id).orElse(null);
		
		if (jobPost == null) {
			throw new ResourceNotFoundException("Job Not Found");
		}
		
		return jobPost;
	}

	public void updateJobById(String id, JobPost job) {
		
		if (!job.getId().equals(id)) {
			throw new InvalidInputException("Job Id and input object Id doesn,t match");
		}
		
		if (jobPostRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Job Application Not Found");
		}
		
		jobPostRepository.save(job);
		
	}

	public void deleteJobById(String id) {
		
		if (jobPostRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Job Application Not Found");
		}
		
		jobPostRepository.deleteById(id);
	}

}
