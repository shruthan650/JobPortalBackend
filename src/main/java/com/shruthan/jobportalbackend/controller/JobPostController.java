package com.shruthan.jobportalbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shruthan.jobportalbackend.model.JobPost;
import com.shruthan.jobportalbackend.service.JobPostService;

@RestController
@RequestMapping("/api")
public class JobPostController {

	@Autowired
	JobPostService service;

	@GetMapping("/greet")
	public String greet() {
		return "Welcome to JobPortal";
	}

	@PostMapping("/job")
	@PreAuthorize("hasRole('RECRUITER')")
	public JobPost addJob(@RequestBody JobPost job) {
		return service.addJob(job);
	}

	@GetMapping("/jobs")
	public List<JobPost> getAllJobs() {
		return service.getAllJobs();
	}

	@GetMapping("/job/{id}")
	@PreAuthorize("hasRole('RECRUITER')")
	public JobPost getJobById(@PathVariable String id) {
		return service.getJobById(id);
	}

	@PutMapping("/job/{id}")
	@PreAuthorize("hasRole('RECRUITER')")
	public void updateJobById(@PathVariable String id, @RequestBody JobPost job) {
		service.updateJobById(id, job);
	}

	@DeleteMapping("/jobs/{id}")
	@PreAuthorize("hasRole('RECRUITER')")
	public void deleteJobById(@PathVariable String id) {
		service.deleteJobById(id);
	}
}
