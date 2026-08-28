package com.shruthan.jobportalbackend.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "ApplicationRepo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

	@Id
	private String id;
	private String jobId;
	private String candidateId;
	private LocalDateTime appliedAt;
	private String status;
}
