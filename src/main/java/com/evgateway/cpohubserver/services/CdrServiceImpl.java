package com.evgateway.cpohubserver.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.evgateway.cpohubserver.Utils.Utils;
import com.evgateway.cpohubserver.cnum.ERole;
import com.evgateway.cpohubserver.exception.DataNotFoundException;
import com.evgateway.cpohubserver.model.CPOCdrModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.request.PageResult;

@Service
public class CdrServiceImpl implements CdrService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserService userService;

	@Override
	public PageResult<CPOCdrModel> getCDRTableData(int pagesize, int page, Map<String, List<String>> filters) {
		Query query = new Query();
		User currentUser = userService.getCurrentUser();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			query.addCriteria(Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
					.is(currentUser.getCountry_code()));

		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			query.addCriteria(Criteria.where("emsp_party_id").is(currentUser.getParty_id()).and("emsp_country_code")
					.is(currentUser.getCountry_code()));

		}
		List<String> values = null;

		if (filters != null && !filters.isEmpty()) {
			List<String> keys = filters.get("key");
			values = filters.get("value");

			if (keys != null && values != null && !keys.isEmpty() && !values.isEmpty()) {
				List<Criteria> orCriteria = new ArrayList<>();

				for (String key : keys) {
					for (String value : values) {
						if (key != null && value != null) {
							if (key.equals("start_date_time")) {
								// Handle date search for 'last_updated'
								try {
									Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(value.trim());
									orCriteria.add(Criteria.where(key).gte(date));
								} catch (ParseException e) {
									// Handle invalid date format gracefully
//		                            
								}
							} else if (key.equals("evse_id")) {
								// Handle evse_id with regex
								orCriteria.add(Criteria.where("evse_id").regex(value.trim().replace("*", "\\*"), "i"));
							} else {
								// Handle other keys with regex
								orCriteria.add(Criteria.where(key).regex(".*" + value.trim() + ".*", "i"));
							}
						}
					}
				}

				// Apply OR condition to the query
				if (!orCriteria.isEmpty()) {
					query.addCriteria(new Criteria().orOperator(orCriteria.toArray(new Criteria[0])));

				}
			}
		}
		Pageable pageable = PageRequest.of(page, pagesize);

		query.with(Sort.by(Sort.Order.desc("last_updated")));
		List<CPOCdrModel> cdrs = mongoTemplate.find(query.with(pageable), CPOCdrModel.class);

		long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOCdrModel.class);

		Page<CPOCdrModel> cdrss = new PageImpl<>(cdrs, pageable, count);
		PageResult<CPOCdrModel> pagedResult = new PageResult<>(cdrss);

		if (pagedResult.getTotalElements() == 0) {

			if (values == null || values.stream().allMatch(Objects::isNull)) {
			} else {
				throw new DataNotFoundException("No data found for the given search criteria");
			}
		}

		return pagedResult;

	}

	@Override
	public Map<String, Object> getCdrById(String id) {

		List<Map<String, Object>> resultList = new ArrayList<>();

		User currentUser = userService.getCurrentUser();

		// Step 1: Create the base criteria
		Criteria criteria = Criteria.where("id").is(id);

		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			criteria = criteria.and("cpo_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i")
					.and("cpo_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i");
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			criteria = criteria.and("emsp_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i")
					.and("emsp_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i");
		}

		// Step 2: Build the aggregation pipeline with a match stage
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria) // Use manually constructed
																							// criteria
		);

		// Step 3: Execute aggregation query
		List<Map> find = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class).getMappedResults();

		if (find.size() > 0) {

			resultList = find.stream().map(map -> {
				Map<String, Object> modifiedMap = new HashMap<>(map); // Create a copy to avoid modifying original
																		// map
																		if (modifiedMap.containsKey("total_time")) {
																			double hours = Double.parseDouble(String.valueOf(modifiedMap.get("total_time")));
																			
																			// Convert to total seconds, then round to nearest full minute
																			int totalSeconds = (int) Math.round(hours * 3600);
																			totalSeconds = (int) (Math.round(totalSeconds / 60.0) * 60); // Round to nearest minute in seconds
																		
																			// Convert back to hours format for Utils.getTimeFormate
																			double roundedHours = totalSeconds / 3600.0;
																		
																			modifiedMap.put("total_time", Utils.getTimeFormate(roundedHours));
																		}
																		
				return modifiedMap;
			}).collect(Collectors.toList());
			
			







			return resultList.get(0);

		}
		return null;

	}

}
