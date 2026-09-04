package com.shruthan.jobportalbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "UserRepo")
public class User {

	@Id
	private String id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String role;

}
