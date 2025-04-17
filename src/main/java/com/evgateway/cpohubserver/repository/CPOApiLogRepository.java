package com.evgateway.cpohubserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.evgateway.cpohubserver.model.CPOHUBApiLogModel;

public interface CPOApiLogRepository extends MongoRepository<CPOHUBApiLogModel, String> {
}
