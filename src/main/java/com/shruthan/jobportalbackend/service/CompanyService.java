package com.shruthan.jobportalbackend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.exception.InvalidInputException;
import com.shruthan.jobportalbackend.exception.ResourceNotFoundException;
import com.shruthan.jobportalbackend.model.Company;
import com.shruthan.jobportalbackend.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

	public List<Company> getAllCompanies() {
		logger.info("All application fetched");
		return companyRepository.findAll();
	}

	public Company addCompany(Company company) {
		return companyRepository.save(company);
	}

	public Company getCompanyById(String id) {
		
		Company company = companyRepository.findById(id).orElse(null);
		
		if (company == null) {
			logger.debug("ResourceNotFoundException thrown from getCompanyById service");
			throw new ResourceNotFoundException("Company Not Found");
		}
		
		return company;
	}

	public void updateCompanyById(Company company, String id) {
		
		if (!company.getId().equals(id)) {
			logger.debug("InvalidInputException thrown from updateCompanyById service");
			throw new InvalidInputException("Company Id and input object Id doesn,t match");
		}
		
		if (companyRepository.findById(id).isEmpty()) {
			logger.debug("ResourceNotFoundException thrown from updateCompanyById service");
			throw new ResourceNotFoundException("Company Not Found");
		}
		
		companyRepository.save(company);
		
	}

	public void deleteCompanyById(String id) {
		
		if (companyRepository.findById(id).isEmpty()) {
			logger.debug("ResourceNotFoundException thrown from deleteCompanyById service");
			throw new ResourceNotFoundException("Company Not Found");
		}
		companyRepository.deleteById(id);
	}

}
