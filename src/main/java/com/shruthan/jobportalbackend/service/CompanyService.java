package com.shruthan.jobportalbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shruthan.jobportalbackend.model.Company;
import com.shruthan.jobportalbackend.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public Company addCompany(Company company) {
		return companyRepository.save(company);
	}

	public Company getCompanyById(String id) {
		return companyRepository.findById(id).orElse(null);
	}

	public void updateCompanyById(Company company, String id) {
		companyRepository.save(company);
	}

	public void deleteCompanyById(String id) {
		companyRepository.deleteById(id);
	}

}
