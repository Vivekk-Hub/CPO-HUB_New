package com.evgateway.cpohubserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOHUBCredentialModel;

@Repository
public interface CPOHUBCredentialRepository extends MongoRepository<CPOHUBCredentialModel, String> {

	@Query("{ 'accessToken': ?0 }")
	Optional<CPOHUBCredentialModel> findByAccessToken(String accessToken);

	@Query("{ 'party_id': ?0, 'role': ?1, 'country': ?2 }")
	Optional<CPOHUBCredentialModel> findFullOCPICredential(String partyId, String role, String country);

	@Query(value = "{ 'party_id': ?0, 'country_code': ?1 }")
	Optional<CPOHUBCredentialModel> findByPartyIdAndCountryCode(String partyId, String countryCode);

	@Query(value = "{ 'party_id': ?0 }")
	Optional<CPOHUBCredentialModel> findOCPICredentialByPartyId(String partyId);

	List<CPOHUBCredentialModel> findAll();

}
