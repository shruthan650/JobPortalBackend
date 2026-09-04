package com.shruthan.jobportalbackend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.exception.InvalidInputException;
import com.shruthan.jobportalbackend.exception.ResourceNotFoundException;
import com.shruthan.jobportalbackend.model.Application;
import com.shruthan.jobportalbackend.model.ApplicationStatus;
import com.shruthan.jobportalbackend.repository.ApplicationRepository;
import org.slf4j.Logger;

@Service
public class ApplicationService {
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

	public List<Application> getAllApplications() {
		logger.info("All application fetched");
		return applicationRepository.findAll();
	}

	public Application addApplication(Application application) {
		
		if (application.getAppliedAt() == null) {
			application.setAppliedAt(LocalDateTime.now());
		}
		
		application.setStatus(ApplicationStatus.APPLIED);
		
		return applicationRepository.save(application);
	}

	public Application getApplicationById(String id) {
		Application application = applicationRepository.findById(id).orElse(null);
		
		if (application == null) {
			logger.debug("ResourceNotFoundException thrown from getApplicationById service");
			throw new ResourceNotFoundException("Job Application Not Found");
		}
		
		return application;
	}

	public void updateApplicationById(Application application, String id) {
		
		if (!application.getId().equals(id)) {
			logger.debug("InvalidInputException thrown from updateApplication service");
			throw new InvalidInputException("Job Application Id and input object Id doesn't match");
		}
		
		if (applicationRepository.findById(id).isEmpty()) {
			logger.debug("ResourceNotFoundException thrown from updateApplicationById service");
			throw new ResourceNotFoundException("Job Application Not Found");
		}
		
		Application existing = applicationRepository
				.findById(id)
				.orElseThrow(
						() -> (new ResourceNotFoundException("Job Application Not Found thrown from updateApplication")));
		
		ApplicationStatus current = existing.getStatus();
		ApplicationStatus requested = application.getStatus();
		
		if(!current.canTransistionTo(requested)) {
			logger.warn("Cannot change status from {} to {}", existing, requested);
			throw new InvalidInputException("Cannot change status from " + existing + " to " + requested);
		}
		
		if (!existing.getJobId().equals(application.getJobId()) || 
			!existing.getCandidateId().equals(application.getCandidateId())
			) {
			throw new InvalidInputException("Cannot change field other than status while updating application");
		}
		
		existing.setStatus(requested);
		
		applicationRepository.save(existing);	
	}

	public void deleteApplicationById(String id) {
		
		if (applicationRepository.findById(id).isEmpty()) {
			logger.debug("ResourceNotFoundException thrown from deleteApplicationById service");
			throw new ResourceNotFoundException("Job Application Not Found");
		}
		applicationRepository.deleteById(id);
	}

}
