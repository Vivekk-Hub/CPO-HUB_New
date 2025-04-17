package com.evgateway.cpohubserver.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOEndpointModel;

@Repository
public interface CPOEndpointRepository extends MongoRepository<CPOEndpointModel, String> {

	@Query("{ 'party_id': ?0,'country_code': ?1 }")
	List<CPOEndpointModel> findByPartyIdAndCountryCode(String party_id, String country_code);

	@Query("{ 'identifier' : { '$regex' : ?0, '$options' : 'i' }, 'party_id': ?1}")
	CPOEndpointModel findEndPoints(String identifier, String partyId);
	
}
