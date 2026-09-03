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

import com.shruthan.jobportalbackend.model.Company;
import com.shruthan.jobportalbackend.service.CompanyService;

@RestController
@RequestMapping("/api")
public class CompanyController {
	
	@Autowired
	CompanyService service;

	@GetMapping("/companies")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Company> getAllCompanies() {
		return service.getAllCompanies();
	}
	
	@PostMapping("/company")
	@PreAuthorize("hasRole('RECRUITER')")
	public Company addCompany(@RequestBody Company company) {
		return service.addCompany(company);
	}
	
	@GetMapping("/company/{id}")
	@PreAuthorize("hasRole('ADMIN') or (hasRole('RECRUITER') and @SecurityService.isCompanyOwner(#id))")
	public Company getCompanyById(@PathVariable String id) {
		return service.getCompanyById(id);
	}
	
	@PutMapping("/company/{id}")
	@PreAuthorize("hasRole('ADMIN') or (hasRole('RECRUITER') and @SecurityService.isCompanyOwner(#id))")
	public void updateCompanyById(@RequestBody Company company, @PathVariable String id) {
		service.updateCompanyById(company, id);
	}
	
	@DeleteMapping("/company/{id}")
	@PreAuthorize("hasRole('ADMIN') or (hasRole('RECRUITER') and @SecurityService.isCompanyOwner(#id))")
	public void deleteCompanyById(@PathVariable String id) {
		service.deleteCompanyById(id);
	}
}
