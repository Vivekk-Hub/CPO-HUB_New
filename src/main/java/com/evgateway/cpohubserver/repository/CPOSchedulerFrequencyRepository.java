package com.evgateway.cpohubserver.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOSchedulerFrequencyModel;

@Repository
public interface CPOSchedulerFrequencyRepository extends MongoRepository<CPOSchedulerFrequencyModel, String> {

	Optional<CPOSchedulerFrequencyModel> findByIdentifierAndPartyIdAndFrequency(String identifier, String partyId,long frequency);

}
