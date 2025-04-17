package com.evgateway.cpohubserver.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOHUBEMSPPermission;

@Repository
public interface CPOHUBEMSPPermissionRepository extends MongoRepository<CPOHUBEMSPPermission, String> {


	@Query("{ 'emsp_party_id' : ?0 }")
	Optional<CPOHUBEMSPPermission> findByEmspPartyId(String emsp_party_id);

}