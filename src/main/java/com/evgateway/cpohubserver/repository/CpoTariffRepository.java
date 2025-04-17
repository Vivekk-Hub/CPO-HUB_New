package com.evgateway.cpohubserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOTariffModel;

@Repository
public interface CpoTariffRepository extends MongoRepository<CPOTariffModel, String> {

}
