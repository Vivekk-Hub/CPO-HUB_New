package com.evgateway.cpohubserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.evgateway.cpohubserver.model.CPOCdrModel;


public interface CpoCdrRepository extends MongoRepository<CPOCdrModel, String>{

	@Query("{ 'id': ?0 }")
	CPOCdrModel findByCdrId(String id);

}
