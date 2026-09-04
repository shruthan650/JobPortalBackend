package com.shruthan.jobportalbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "CompanyRepo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	@Id
	private String id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String location;
	
	@NotBlank
	private String website;
	
	@NotBlank
	private String recruiterId;

}
