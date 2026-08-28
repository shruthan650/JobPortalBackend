package com.shruthan.jobportalbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.model.Application;
import com.shruthan.jobportalbackend.repository.ApplicationRepository;

@Service
public class ApplicationService {
	
	ApplicationRepository applicationRepository;

	public List<Application> getAllApplications() {
		return applicationRepository.findAll();
	}

	public Application addApplication(Application application) {
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
