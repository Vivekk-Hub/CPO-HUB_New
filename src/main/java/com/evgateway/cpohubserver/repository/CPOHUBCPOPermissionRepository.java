package com.evgateway.cpohubserver.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOHUBCPOPermission;

@Repository
public interface CPOHUBCPOPermissionRepository extends MongoRepository<CPOHUBCPOPermission, String> {

	@Query("{ 'party_id' : ?0 }")
	Optional<CPOHUBCPOPermission> findByPartyId(String party_id);

}
