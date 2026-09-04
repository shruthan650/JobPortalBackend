package com.shruthan.jobportalbackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "JobPostRepo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPost {

	@Id
	private String id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String profile;
	
	@NotNull
	private Integer experience;
	
	@NotEmpty
	private String[] technologies;
	
	@NotBlank
	private String location;
	
	@NotNull
	private BigDecimal salary;
	
	@NotBlank
	private String companyId;
	
	@NotBlank
	private String recruiterId;
	
	private LocalDateTime createdAt = LocalDateTime.now();
}
