package com.evgateway.cpohubserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOCdrModel;

@Repository
public interface CPOHUBDataAnalyticsRepository extends MongoRepository<CPOCdrModel, String> {

}
