package com.itzst.tracktedu.service;

import com.itzst.tracktedu.exception.BatchSubjectAllReadyExistException;
import com.itzst.tracktedu.exception.BatchSubjectNotCreatedException;
import com.itzst.tracktedu.exception.BatchSubjectNotFoundException;
import com.itzst.tracktedu.model.Batch;
import com.itzst.tracktedu.model.BatchSubject;

public interface BatchSubjectService {
	
	public BatchSubject createBatchSubject(BatchSubject batchSubject) throws BatchSubjectAllReadyExistException, BatchSubjectNotCreatedException;
	
	public BatchSubject updateBatchSubject(BatchSubject batchSubject, String batchSubjectId) throws BatchSubjectAllReadyExistException, BatchSubjectNotFoundException;
	
	public Boolean deleteBatchSubject(String batchSubjectId) throws BatchSubjectNotFoundException;
	
	public BatchSubject getBatchSubjectByBatchSubjectId(String batchSubjectId) throws BatchSubjectNotFoundException;

	public BatchSubject getBatchSubjectByBatchId(String batchId)throws BatchSubjectNotFoundException;
	
	
	public Batch getBatchByBatchId(String batchId);
}
