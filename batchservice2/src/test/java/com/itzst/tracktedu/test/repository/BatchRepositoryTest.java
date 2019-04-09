package com.itzst.tracktedu.test.repository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itzst.tracktedu.model.Batch;
import com.itzst.tracktedu.repository.BatchRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BatchRepositoryTest {

	@Autowired
	private BatchRepository batchRepository;
	
	private Batch batch;
	
	@Before
	public void setUp() {
		batch = new Batch();
		batch.setBatchId("fffdd44ffff66vvvv44");
		batch.setSchoolId(1001L);
		batch.setBatchCode("ABC");
		batch.setBatchName("ABC Batch");
		batch.setBatchDescription("ABC Description");
		batch.setIsActive(true);
		batch.setBatchCreated(LocalDateTime.now());
	}
	
	@After
	public void tearDown() {
		batchRepository.deleteAll();
	}
	
	@Test
	public void createBatchTest() {
		batchRepository.insert(batch);
		
		Batch fetchedBatch = batchRepository.findById(batch.getBatchId()).get();
		Assert.assertEquals("fffdd44ffff66vvvv44", fetchedBatch.getBatchId());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void deleteBatchTest() {
		
		batchRepository.insert(batch);
		Batch fetchedBatch = batchRepository.findById(batch.getBatchId()).get();
		Assert.assertEquals("fffdd44ffff66vvvv44", fetchedBatch.getBatchId());
		
		batchRepository.delete(fetchedBatch);
		fetchedBatch = batchRepository.findById(batch.getBatchId()).get();
		
	}
	
	@Test
	public void updateBatchTest() {
		
		batchRepository.insert(batch);
		Batch fetchedBatch = batchRepository.findById(batch.getBatchId()).get();
		Assert.assertEquals("fffdd44ffff66vvvv44", fetchedBatch.getBatchId());
		
		fetchedBatch.setBatchDescription("ABC Batch Description Updated.");
		batchRepository.save(fetchedBatch);
		
		fetchedBatch = batchRepository.findById(batch.getBatchId()).get();
		Assert.assertEquals("ABC Batch Description Updated.", fetchedBatch.getBatchDescription());
		
	}

}// EndOfClass
