package com.evgateway.cpohubserver.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.CPOHUBDowntimeModel;

@Repository
public interface DownTimeRepository extends MongoRepository<CPOHUBDowntimeModel, String> {

	@Query("{ 'partnerId': ?0, $or: [ { 'startTime': { $gte: ?1, $lte: ?2 } }, { 'endTime': { $gte: ?1, $lte: ?2 } } ] }")
	List<CPOHUBDowntimeModel> findByPartnerIdAndTimeRange(String partnerId, Instant from, Instant to);

	@Query("{ 'partnerId': ?0 }")
	List<CPOHUBDowntimeModel> findAllByPartnerId(String partnerId);
}
