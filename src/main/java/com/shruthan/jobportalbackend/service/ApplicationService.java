package com.shruthan.jobportalbackend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		if (application.getAppliedAt() != null) {
			application.setAppliedAt(LocalDateTime.now());
		}
		return applicationRepository.save(application);
	}

	public Application getApplicationById(String id) {
		return applicationRepository.findById(id).orElse(null);
	}

	public void updateApplicationById(Application application, String id) {
		applicationRepository.save(application);
	}

	public void deleteApplicationById(String id) {
		applicationRepository.deleteById(id);
	}

}
