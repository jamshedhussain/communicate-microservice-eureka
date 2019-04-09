package com.itzst.tracktedu.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data

@Document
public class Batch {
	
	@Id
	private String batchId;
	private Long schoolId;
	private String batchCode;
	private String batchName;
	private String batchDescription;
	private Boolean isActive;
	private LocalDateTime batchCreated;
	
}