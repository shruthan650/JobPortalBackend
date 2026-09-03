package com.shruthan.jobportalbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private String name;
	private String description;
	private String location;
	private String website;
	private String recruiterId;

}
