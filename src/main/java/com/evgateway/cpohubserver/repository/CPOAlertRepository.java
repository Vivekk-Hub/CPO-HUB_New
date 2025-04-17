package com.evgateway.cpohubserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.evgateway.cpohubserver.model.CPOHUBAlertModel;

public interface CPOAlertRepository extends MongoRepository<CPOHUBAlertModel, String> {

	CPOHUBAlertModel findAlertById(String id);

	

}