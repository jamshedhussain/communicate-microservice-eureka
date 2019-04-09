package com.itzst.tracktedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itzst.tracktedu.exception.BatchSubjectAllReadyExistException;
import com.itzst.tracktedu.exception.BatchSubjectNotCreatedException;
import com.itzst.tracktedu.exception.BatchSubjectNotFoundException;
import com.itzst.tracktedu.model.Batch;
import com.itzst.tracktedu.model.BatchSubject;
import com.itzst.tracktedu.model.Subject;
import com.itzst.tracktedu.service.BatchSubjectService;

@RestController
@RequestMapping("/api/v1/")
public class BatchSubjectController {
	
	@Autowired
	private BatchSubjectService batchSubjectService;
	
	public BatchSubjectController(BatchSubjectService batchSubjectService) {
		this.batchSubjectService = batchSubjectService;
	}//EndOfCostructor
	
	@GetMapping("batch/{id}")
	public ResponseEntity<?> getBatchById(@PathVariable("id") String batchId){
	
			Batch batch = batchSubjectService.getBatchByBatchId(batchId);
			return new ResponseEntity<>(batch, HttpStatus.OK);
		
	}//EndOFGetBatchById()

	
	
	
	@PostMapping("batchSubject")
	public ResponseEntity<?> createBatchSubject(@RequestBody BatchSubject batchSubject){
		
		try {
			BatchSubject bs = batchSubjectService.createBatchSubject(batchSubject);
			if(bs != null) {
				return new ResponseEntity<>(bs, HttpStatus.CREATED);
			}
		} catch (BatchSubjectAllReadyExistException |BatchSubjectNotCreatedException e) {
			return new ResponseEntity<>("Unable to create batchSubject.\n "+e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Unable to create batchSubject. ", HttpStatus.INTERNAL_SERVER_ERROR);	
		
	}//EndOfCreateBatch()

	@PutMapping("batchSubject/{id}")
	public ResponseEntity<?> updateBatchSubject(@RequestBody BatchSubject batchSubject, @PathVariable("id") String batchSubjectId){
		
		if(!batchSubjectId.equals(batchSubject.getBatchSubjectId())) {
			return new ResponseEntity<>("Unable to update batch. ", HttpStatus.BAD_REQUEST);
		}
		
		try {
			BatchSubject bs = batchSubjectService.updateBatchSubject(batchSubject, batchSubjectId);
			if(bs != null) {
				return new ResponseEntity<>(bs, HttpStatus.OK);
			}
		} catch (BatchSubjectNotFoundException | BatchSubjectAllReadyExistException e) {
			return new ResponseEntity<>("Unable to update batchSubject.\n "+e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Unable to update batchSubject. ", HttpStatus.INTERNAL_SERVER_ERROR);
	}//EndOfUpdateBatch()
	
	@DeleteMapping("batchSubject/{id}")
	public ResponseEntity<?> deleteBatchSubject(@PathVariable("id") String batchSubjectId){
		
		try {
			Boolean flag = batchSubjectService.deleteBatchSubject(batchSubjectId);
			if(flag) {
				return new ResponseEntity<>("BatchSubject deleted successfully.", HttpStatus.OK);
			}
		} catch (BatchSubjectNotFoundException e) {
			return new ResponseEntity<>("Unable to delete batchSubject.\n "+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Unable to delete batchSubject.", HttpStatus.INTERNAL_SERVER_ERROR);
	}//EndOfDeleteBatch()
	
	@GetMapping("batchSubject/{id}")
	public ResponseEntity<?> getBatchSubjectById(@PathVariable("id") String batchSubjectId){
		try {
			BatchSubject batchSubject = batchSubjectService.getBatchSubjectByBatchSubjectId(batchSubjectId);
			return new ResponseEntity<>(batchSubject, HttpStatus.OK);
		} catch (BatchSubjectNotFoundException e) {
			return new ResponseEntity<>("Unable to get batchSubject.\n"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}//EndOFGetBatchById()
	
	@GetMapping("test")
	public ResponseEntity<?> getTestData(){
		BatchSubject bs = new BatchSubject();
		bs.setBatchSubjectId("fdfdfd");
		bs.setBatchSubjectCode("sdfsfds Code");
		
		Batch batch=new Batch();
		batch.setBatchId("B.Id");
		batch.setBatchCode("B.code");
		batch.setBatchName("BName");
		batch.setBatchDescription("BDesc");
		
		bs.setBatch(batch);
		
		
		
		List<Subject> subjects=new ArrayList<Subject>();

		Subject s1=new Subject();
		s1.setSchoolId(1001L);
		s1.setSubjectCode("SCode1");
		s1.setSubjectDescription("SDesc1");
		s1.setSubjectName("SName1");
		
		Subject s2=new Subject();
		s2.setSchoolId(1001L);
		s2.setSubjectCode("SCode2");
		s2.setSubjectDescription("SDesc2");
		s2.setSubjectName("SName2");
		
		subjects.add(s1);
		subjects.add(s2);
		
		bs.setSubjects(subjects);
	
		
		return new ResponseEntity<>(bs, HttpStatus.OK);
	}//EndOfTest()
	
	
}//class
