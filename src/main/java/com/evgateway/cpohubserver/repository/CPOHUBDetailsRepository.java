package com.evgateway.cpohubserver.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.evgateway.cpohubserver.model.CPOHUBDetails;

public interface CPOHUBDetailsRepository extends MongoRepository<CPOHUBDetails, String> {

	@Query(value = "partyId : ?0,countryCode : ?1")
	List<CPOHUBDetails> findByPartyIdAndCountryCode(String party_id, String country_code);
}
