package com.shruthan.jobportalbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

	@Id
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String role;

}
