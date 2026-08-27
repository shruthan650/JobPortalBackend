package com.shruthan.jobportalbackend.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

	@Id
	private int id;
	private int jobId;
	private int candidateId;
	private Date appliedAt;
	private String status;
}
