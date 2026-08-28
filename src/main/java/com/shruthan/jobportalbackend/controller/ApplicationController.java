package com.shruthan.jobportalbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.shruthan.jobportalbackend.model.Application;
import com.shruthan.jobportalbackend.service.ApplicationService;

public class ApplicationController {

	@Autowired
	ApplicationService service;

	@GetMapping("/applications")
	public List<Application> getAllApplications() {
		return service.getAllApplications();
	}
	
	@PostMapping("/application")
	public Application addApplication(@RequestBody Application application) {
		return service.addApplication(application);
	}
	
	@GetMapping("/application/{id}")
	public Application getApplicationById(@PathVariable String id) {
		return service.getApplicationById(id);
	}
	
	@PutMapping("/application/{id}")
	public void updateApplicationById(@RequestBody Application application, @PathVariable String id) {
		service.updateApplicationById(application, id);
	}
	
	@DeleteMapping("/application/{id}")
	public void deleteApplicationById(@PathVariable String id) {
		service.deleteApplicationById(id);
	}
}
