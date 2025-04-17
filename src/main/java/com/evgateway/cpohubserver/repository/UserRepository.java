package com.evgateway.cpohubserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value = "party_id : ?0,country_code : ?1")
	List<User> findByPartyIdAndCountryCode(String party_id, String country_code);

}
