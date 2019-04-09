package com.itzst.tracktedu.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.itzst.tracktedu.model.BatchSubject;

public interface BatchSubjectRepository extends MongoRepository<BatchSubject, String> {
	public Optional<BatchSubject> findByBatchSubjectCode(String batchSubjectCode);
	
}
