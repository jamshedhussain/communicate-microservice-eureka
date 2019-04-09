package com.itzst.tracktedu.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data

@Document
public class BatchSubject {
	
	@Id
	private String batchSubjectId;
	private String batchSubjectCode;
	private Batch batch;
	private List<Subject> subjects;
	
	private LocalDateTime batchSubjectCreatedOn;
}
