package com.itzst.tracktedu.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.itzst.tracktedu.exception.BatchSubjectAllReadyExistException;
import com.itzst.tracktedu.exception.BatchSubjectNotCreatedException;
import com.itzst.tracktedu.exception.BatchSubjectNotFoundException;
import com.itzst.tracktedu.model.Batch;
import com.itzst.tracktedu.model.BatchSubject;
import com.itzst.tracktedu.repository.BatchSubjectRepository;

@Service
public class BatchSubjectServiceImpl implements BatchSubjectService {

	
	@Autowired
	protected RestTemplate restTemplate;

	protected String serviceUrl;
	public BatchSubjectServiceImpl() {
	}//constructor1
	
	public BatchSubjectServiceImpl(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
		System.out.println("MyUrl"+serviceUrl);
	}//constructor2
	
	
	public Batch getBatchByBatchId(String batchId) {
		return restTemplate.getForObject(serviceUrl + "/api/v1/batch/{id}", Batch.class, batchId);
		
	}
	
	
	
	
	@Autowired
	private BatchSubjectRepository batchSubjectRepository;
	
	public BatchSubjectServiceImpl(BatchSubjectRepository batchSubjectRepository) {
		this.batchSubjectRepository=batchSubjectRepository;
	}
	
	@Override
	public BatchSubject createBatchSubject(BatchSubject batchSubject)
			throws BatchSubjectAllReadyExistException, BatchSubjectNotCreatedException {
		Optional<BatchSubject> optBatchSubject = batchSubjectRepository.findByBatchSubjectCode(batchSubject.getBatchSubjectCode());
			
		if (optBatchSubject.isPresent()) {   
			if (batchSubject.getBatchSubjectId().equalsIgnoreCase(optBatchSubject.get().getBatchSubjectId())) {
				throw new BatchSubjectAllReadyExistException("BatchSubject all ready exist.");
			}
			batchSubject.setBatchSubjectCreatedOn(LocalDateTime.now());
		}
			return batchSubjectRepository.insert(batchSubject);
		}

	@Override
	public BatchSubject updateBatchSubject(BatchSubject batchSubject, String batchSubjectId)
			throws BatchSubjectAllReadyExistException, BatchSubjectNotFoundException {
		Optional<BatchSubject> optBatchSubject = batchSubjectRepository.findById(batchSubjectId);
		if(optBatchSubject.isPresent()) {
			optBatchSubject = batchSubjectRepository.findById(batchSubject.getBatchSubjectId());
			if(optBatchSubject.isPresent() && !optBatchSubject.get().getBatchSubjectId().equals(batchSubjectId)) {
				throw new BatchSubjectAllReadyExistException("BatchSubject allready exist.");
			}
			return batchSubjectRepository.save(batchSubject);
		}else {
			throw new BatchSubjectNotFoundException("BatchSubject not found for update");
		}
	}

	@Override
	public Boolean deleteBatchSubject(String batchSubjectId) throws BatchSubjectNotFoundException {
		Optional<BatchSubject> optBatchSubject = batchSubjectRepository.findById(batchSubjectId);
		if(optBatchSubject.isPresent()) {
			batchSubjectRepository.deleteById(batchSubjectId);
			return true;
		}else {
			throw new BatchSubjectNotFoundException("BatchSubject not found for delete.");
		}
	}

	@Override
	public BatchSubject getBatchSubjectByBatchSubjectId(String batchSubjectId) throws BatchSubjectNotFoundException {
		Optional<BatchSubject> optBatchSubject = batchSubjectRepository.findById(batchSubjectId);
		if(optBatchSubject.isPresent()) {
			return optBatchSubject.get();
		}else {
			throw new BatchSubjectNotFoundException("BatchSubject not found.");
		}
	}

	@Override
	public BatchSubject getBatchSubjectByBatchId(String batchId) throws BatchSubjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
