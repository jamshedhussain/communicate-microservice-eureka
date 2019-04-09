package com.itzst.tracktedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.itzst.tracktedu.service.BatchSubjectService;
import com.itzst.tracktedu.service.BatchSubjectServiceImpl;

@EnableEurekaClient
@SpringBootApplication
public class BatchSubjectApplication {

	public static final String PROFILES_SERVICE_URL = "http://BATCH-PRODUCER-SERVICE";
	
	public static void main(String[] args) {
		
		SpringApplication.run(BatchSubjectApplication.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public BatchSubjectService batchSubjectService() {
		return new BatchSubjectServiceImpl(PROFILES_SERVICE_URL);
	}

}
