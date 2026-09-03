package com.shruthan.jobportalbackend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.exception.InvalidInputException;
import com.shruthan.jobportalbackend.exception.ResourceNotFoundException;
import com.shruthan.jobportalbackend.model.Application;
import com.shruthan.jobportalbackend.repository.ApplicationRepository;

@Service
public class ApplicationService {
	
	@Autowired
	ApplicationRepository applicationRepository;

	public List<Application> getAllApplications() {
		return applicationRepository.findAll();
	}

	public Application addApplication(Application application) {
		
		if (application.getAppliedAt() == null) {
			application.setAppliedAt(LocalDateTime.now());
		}
		return applicationRepository.save(application);
	}

	public Application getApplicationById(String id) {
		Application application = applicationRepository.findById(id).orElse(null);
		
		if (application == null) {
			throw new ResourceNotFoundException("Job Application Not Found");
		}
		
		return application;
	}

	public void updateApplicationById(Application application, String id) {
		
		if (!application.getId().equals(id)) {
			throw new InvalidInputException("Job Application Id and input object Id doesn't match");
		}
		
		if (applicationRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Job Application Not Found");
		}
		
		applicationRepository.save(application);	
	}

	public void deleteApplicationById(String id) {
		
		if (applicationRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Job Application Not Found");
		}
		applicationRepository.deleteById(id);
	}

}
