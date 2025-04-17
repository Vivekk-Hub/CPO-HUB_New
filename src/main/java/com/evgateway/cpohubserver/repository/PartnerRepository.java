package com.evgateway.cpohubserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOHUBPartnerModel;

@Repository
public interface PartnerRepository extends MongoRepository<CPOHUBPartnerModel, String> {

	public List<CPOHUBPartnerModel> findAll();

	public List<CPOHUBPartnerModel> findByRole(String role);

	public Optional<CPOHUBPartnerModel> findById(String id);

	@Query("{ 'party_id' : ?0, 'country_code' : ?1 }")
	Optional<CPOHUBPartnerModel> findPartnerByPartyIdAndCountryCode(String partyId, String countryCode);
	
	@Query("{ 'party_id' : ?0 }")
	CPOHUBPartnerModel findPartnerByPartyId(String partyId);

}
