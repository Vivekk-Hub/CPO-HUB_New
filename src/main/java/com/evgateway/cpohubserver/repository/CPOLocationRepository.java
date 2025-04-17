package com.evgateway.cpohubserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOLocationModel;

@Repository
public interface CPOLocationRepository extends MongoRepository<CPOLocationModel, String> {

	CPOLocationModel findByUid(String id);

}
