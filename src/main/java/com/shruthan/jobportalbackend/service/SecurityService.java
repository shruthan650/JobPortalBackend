package com.shruthan.jobportalbackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.repository.ApplicationRepository;
import com.shruthan.jobportalbackend.repository.CompanyRepository;
import com.shruthan.jobportalbackend.repository.JobPostRepository;
import com.shruthan.jobportalbackend.repository.UserRepository;

import com.shruthan.jobportalbackend.model.Application;
import com.shruthan.jobportalbackend.model.Company;
import com.shruthan.jobportalbackend.model.JobPost;
import com.shruthan.jobportalbackend.model.User;

@Service
public class SecurityService {

	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	JobPostRepository jobPostRepository;
	
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	UserRepository userRepository;

	public boolean isApplicationOwner(String applicationId) {

		Optional<Application> application = applicationRepository.findById(applicationId);

		if (application.isEmpty()) {
			return false;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User user = userRepository.findByEmail(email);

		if (user == null) {
			return false;
		}

		return (user.getId().equals(application.get().getCandidateId()));
	}

	public boolean isJobOwner(String jobId) {

		Optional<JobPost> job = jobPostRepository.findById(jobId);

		if (job.isEmpty()) {
			return false;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User user = userRepository.findByEmail(email);

		if (user == null) {
			return false;
		}

		return (user.getId().equals(job.get().getRecruiterId()));
	}
	
	public boolean isCompanyOwner(String companyId) {

		Optional<Company> company = companyRepository.findById(companyId);

		if (company.isEmpty()) {
			return false;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User user = userRepository.findByEmail(email);

		if (user == null) {
			return false;
		}

		return (user.getId().equals(company.get().getRecruiterId()));
	}
}
