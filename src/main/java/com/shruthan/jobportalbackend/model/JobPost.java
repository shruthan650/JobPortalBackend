package com.shruthan.jobportalbackend.model;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
	private String title;
	private String description;
	private String profile;
	private Integer experience;
	private String[] technologies;
	private String location;
	private BigDecimal salary;
	private Integer companyId;
	private Date createdAt;
}
