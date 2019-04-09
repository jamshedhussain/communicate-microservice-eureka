package com.itzst.tracktedu.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data

@Document
public class Subject {
	@Id
	private String subjectId;
	private Long schoolId;
	private String subjectCode;
	private String subjectName;
	private String subjectDescription;
	private Boolean isActive;
	private LocalDateTime subjectCreatedOn;
	
}//class
