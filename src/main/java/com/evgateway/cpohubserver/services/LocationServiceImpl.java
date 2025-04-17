package com.evgateway.cpohubserver.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.evgateway.cpohubserver.cnum.ERole;
import com.evgateway.cpohubserver.exception.DataNotFoundException;
import com.evgateway.cpohubserver.model.CPOHUBEMSPPermission;
import com.evgateway.cpohubserver.model.CPOLocationModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.repository.CPOHUBEMSPPermissionRepository;
import com.evgateway.cpohubserver.request.EvseWithNestedLocation;
import com.evgateway.cpohubserver.request.PageResult;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserService userService;

	@Autowired
	private CPOHUBEMSPPermissionRepository cpohubemspPermissionRepository;

	@Override
	public PageResult<Map<String, Object>> getTableData(int pagesize, int page, Map<String, List<String>> filters) {
		int skipCount = (page > 0) ? (page - 1) * pagesize : 0;

		User currentUser = userService.getCurrentUser();
		Criteria filterCriteria = new Criteria();

		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {

			filterCriteria.and("locationDetails.cpo_party_id").is(currentUser.getParty_id());
			filterCriteria.and("locationDetails.cpo_country_code").is(currentUser.getCountry_code());


		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {

			filterCriteria.and("locationDetails.cpo_party_id").is(currentUser.getParty_id());
			filterCriteria.and("locationDetails.cpo_country_code").is(currentUser.getCountry_code());



		}

		List<Document> andConditions = new ArrayList<>();

		List<String> values = null;

		if (filters != null && !filters.isEmpty()) {
			List<String> keys = filters.get("key");
			values = filters.get("value");

			if (keys != null && values != null && !keys.isEmpty() && !values.isEmpty()) {
				List<Document> orConditions = new ArrayList<>();
				for (String key : keys) {
					for (String value : values) {
						if (key != null && value != null) {
							if (value.contains("*")) {
								orConditions.add(new Document("cpo_evse_id",
										new Document("$regex", value.trim().replace("*", "\\*")).append("$options",
												"i")));

								orConditions.add(
										new Document("evse_id", new Document("$regex", value.trim().replace("*", "\\*"))
												.append("$options", "i")));

							} else {
								orConditions.add(new Document(key,
										new Document("$regex", ".*" + value.trim() + ".*").append("$options", "i")));
							}
						}
					}
				}

				if (!orConditions.isEmpty()) {
					andConditions.add(new Document("$or", orConditions));
				}
			}
		}

		// Ensure filterCriteria is applied in the match stage
		Document filterCriteriaDocument = filterCriteria.getCriteriaObject();
		if (!filterCriteriaDocument.isEmpty()) {
			andConditions.add(filterCriteriaDocument);
		}
		// Construct the match stage if there are conditions
		AggregationOperation match = null;
		if (!andConditions.isEmpty()) {
			match = context -> new Document("$match", new Document("$and", andConditions));
		}

		AggregationOperation lookup = context -> new Document("$lookup", new Document("from", "ocpi_cpo_location")
				.append("localField", "loc_uid").append("foreignField", "id").append("as", "locationDetails"));

		AggregationOperation unwind = context -> new Document("$unwind", "$locationDetails");

		AggregationOperation project = context -> new Document("$project",
				new Document().append("status", 1).append("uid", 1).append("evseId", "$evse_id")
						.append("last_updated", "$last_updated").append("cpoEvseId", "$cpo_evse_id")
						.append("physicalReference", "$physical_reference")
						.append("location", new Document().append("name", "$locationDetails.name")
								.append("address", new Document("$concat",
										Arrays.asList("$locationDetails.address", ", ", "$locationDetails.city", ", ",
												"$locationDetails.postal_code", ", ", "$locationDetails.country")))
								.append("countryCode", "$locationDetails.cpo_country_code")
								.append("partyId", "$locationDetails.cpo_party_id")
								.append("ids", "$locationDetails.id")));

		AggregationOperation sort = Aggregation.sort(Sort.by(Sort.Order.desc("$last_updated")));
		AggregationOperation skip = Aggregation.skip(skipCount);
		AggregationOperation limit = Aggregation.limit(pagesize);

		// Build the aggregation pipeline
		List<AggregationOperation> operations = new ArrayList<>();
		operations.add(lookup);
		operations.add(unwind);
		if (match != null) {
			operations.add(match);
		}
		operations.add(project);
		operations.add(sort);
		operations.add(skip);
		operations.add(limit);

		Aggregation aggregation = Aggregation.newAggregation(operations);
		// Count total items
		long totalItems = 0;
		if (match != null) {
			Aggregation countAggregation = Aggregation.newAggregation(lookup, unwind, match,
					Aggregation.count().as("totalCount"));
			totalItems = mongoTemplate.aggregate(countAggregation, "ocpi_cpo_evse", Document.class).getMappedResults()
					.stream().findFirst().map(doc -> doc.getInteger("totalCount", 0)).orElse(0);
		} else {
			totalItems = mongoTemplate.count(new Query(), "ocpi_cpo_evse");
		}

		AggregationResults<EvseWithNestedLocation> results = mongoTemplate.aggregate(aggregation, "ocpi_cpo_evse",
				EvseWithNestedLocation.class);
		List<EvseWithNestedLocation> content = results.getMappedResults();

		List<Map<String, Object>> contentAsMapList = content.stream().map(evse -> {
			Map<String, Object> map = new HashMap<>();
			map.put("cpoEvseId", String.valueOf(evse.getCpoEvseId()));
			map.put("evseId", String.valueOf(evse.getEvseId()));
			map.put("status", String.valueOf(evse.getStatus()));
			map.put("uid", String.valueOf(evse.getUid()));
			map.put("physicalReference", String.valueOf(evse.getPhysicalReference()));

			EvseWithNestedLocation.Location location = evse.getLocation();
			if (location != null) {
				Map<String, Object> locationMap = new HashMap<>();
				locationMap.put("name", String.valueOf(location.getName()));
				locationMap.put("address", String.valueOf(location.getAddress()));
				locationMap.put("countryCode", String.valueOf(location.getCountryCode()));
				locationMap.put("partyId", String.valueOf(location.getPartyId()));
				locationMap.put("ids", String.valueOf(location.getIds()));
				map.put("location", locationMap);
			}

			return map;
		}).collect(Collectors.toList());

		Pageable pageable = PageRequest.of(page, pagesize);
		PageResult<Map<String, Object>> pagedResult = new PageResult<>(
				new PageImpl<>(contentAsMapList, pageable, totalItems));

		if (pagedResult.getTotalElements() == 0) {

			if (values == null || values.stream().allMatch(Objects::isNull)) {
			} else {
				throw new DataNotFoundException("No data found for the given search criteria");
			}
		}
		return pagedResult;
	}

	@Override
	public List<Map> getMapData() {
		AggregationOperation project = context -> new Document("$project", new Document().append("id", 1)
				.append("country_code", 1).append("party_id", "$cpo_party_id").append("name", 1)
				.append("address",
						new Document("$concat",
								Arrays.asList("$address", ", ", "$city", ", ", "$postal_code", ", ", "$country")))
				.append("coordinates", 1).append("operator", new Document().append("website", "$operator.website")
						.append("name", "$operator.name").append("logo", "$operator.logo")

				).append("_id", 1));

		Aggregation aggregation = Aggregation.newAggregation(project);
		AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "ocpi_cpo_location", Map.class);

		return results.getMappedResults();
	}

	@Override
	public Object locationDetails(String evseId) {
		User currentUser = userService.getCurrentUser();

		List<AggregationOperation> operations = new ArrayList<>();
		// operations.add(Aggregation.match(Criteria.where("evses.evse_id").is(id)));

		// Add lookup and unwind operations
		operations.add(context -> new Document("$lookup", new Document("from", "ocpi_cpo_evse")
				.append("localField", "id").append("foreignField", "loc_uid").append("as", "evses")));

		operations.add(context -> new Document("$unwind",
				new Document("path", "$evses").append("preserveNullAndEmptyArrays", true)));

		operations.add(Aggregation.match(Criteria.where("evses.evse_id").is(evseId)));

		// Add role-based filtering to aggregation
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			operations.add(
					Aggregation.match(Criteria.where("cpo_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i")
							.and("cpo_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i")));
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			operations.add(Aggregation
					.match(Criteria.where("emsp_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i")
							.and("emsp_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i")));
		}

		operations.add(context -> new Document("$lookup",
				new Document("from", "ocpi_cpo_connector").append("localField", "evses.uid")
						.append("foreignField", "evse_uid").append("as", "evses.connectors")));

		// Grouping and projection
		operations.add(context -> new Document("$group", new Document("_id",
				new Document("id", "$id").append("country_code", "$country_code").append("party_id", "$cpo_party_id")
						.append("publish", "$publish").append("name", "$name").append("address", "$address")
						.append("city", "$city").append("postal_code", "$postal_code").append("country", "$country")
						.append("coordinates", "$coordinates").append("related_locations", "$related_locations")
						.append("publish_allowed_to", "$publish_allowed_to").append("parking_type", "$parking_type")
						.append("operator", "$operator").append("facilities", "$facilities")
						.append("time_zone", "$time_zone").append("opening_times", "$opening_times")
						.append("charging_when_closed", "$charging_when_closed").append("open24x7", "$open24x7")
						.append("last_updated", "$last_updated"))
				.append("evses", new Document("$push", new Document("uid", "$evses.uid")
						.append("evse_id", "$evses.cpo_evse_id").append("status", "$evses.status")
						.append("floor_level", "$evses.floor_level")
						.append("physical_reference", "$evses.physical_reference")
						.append("parking_restrictions", "$evses.parking_restrictions")
						.append("capabilities", "$evses.capabilities").append("last_updated", "$evses.last_updated")
						.append("coordinates", "$evses.coordinates").append("directions", "$evses.directions")
						.append("images", "$evses.images")
						.append("connectors", new Document("$map",
								new Document("input", "$evses.connectors").append("as", "connector").append("in",
										new Document().append("id", "$$connector.id")
												.append("standard", "$$connector.standard")
												.append("format", "$$connector.format")
												.append("power_type", "$$connector.power_type")
												.append("max_voltage", "$$connector.max_voltage")
												.append("max_amperage", "$$connector.max_amperage")
												.append("max_electric_power", "$$connector.max_electric_power")
												.append("terms_and_conditions", "$$connector.terms_and_conditions")
												.append("tariff_ids", "$$connector.tariff_ids")
												.append("last_updated", "$$connector.last_updated"))))))));

		operations.add(context -> new Document("$project", new Document("id", "$_id.id")
				.append("country_code", "$_id.country_code").append("party_id", "$_id.party_id")
				.append("publish", "$_id.publish").append("name", "$_id.name").append("address", "$_id.address")
				.append("city", "$_id.city").append("postal_code", "$_id.postal_code").append("country", "$_id.country")
				.append("coordinates", "$_id.coordinates").append("related_locations", "$_id.related_locations")
				.append("publish_allowed_to", "$_id.publish_allowed_to").append("parking_type", "$_id.parking_type")
				.append("operator", "$_id.operator").append("facilities", "$_id.facilities")
				.append("time_zone", "$_id.time_zone").append("opening_times", "$_id.opening_times")
				.append("charging_when_closed", "$_id.charging_when_closed").append("open24x7", "$_id.open24x7")
				.append("last_updated", "$_id.last_updated").append("evses", "$evses").append("_id", 0)));

		operations.add(Aggregation.sort(Sort.by(Sort.Order.desc("last_updated"))));

		// Execute aggregation
		Aggregation aggregation = Aggregation.newAggregation(operations);
		AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "ocpi_cpo_location", Map.class);

		return results.getMappedResults();

	}

	public List<Map<String, Object>> getCount() {

		User currentUser = userService.getCurrentUser();
		Criteria roleCriteria = new Criteria();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			roleCriteria = Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
					.is(currentUser.getCountry_code());
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {

			Optional<CPOHUBEMSPPermission> permission = cpohubemspPermissionRepository
					.findByEmspPartyId(currentUser.getParty_id());

			if (permission.isPresent())
				roleCriteria = Criteria.where("cpo_party_id").in(permission.get().getCpo_partyIds());

		}

		// Build the aggregation pipeline
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(roleCriteria),
				Aggregation.group("status") // Group by 'status'
						.count().as("count"), // Count documents in each group
				Aggregation.project("count") // Include only 'status' and 'count'
						.and("status").previousOperation());

		// Execute the aggregation
		AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "ocpi_cpo_evse", Map.class);
		List<Map<String, Object>> ls = new ArrayList<>();

		// Initialize the final result map
		Map<String, Object> result = new HashMap<>();
		int totalCount = 0;

		// Iterate over the aggregation results to build the map
		for (Map<String, Object> record : results.getMappedResults()) {
			String status = (String) record.get("status");
			int count = (int) record.get("count");
			result.put(status, count);
			totalCount += count;
		}

		// Add the total count to the result
		result.put("totalPort", totalCount);
		result.put("totalLocation", mongoTemplate.count(new Query(), "ocpi_cpo_location"));
		ls.add(result);

		return ls;
	}

	@Override
	public CPOLocationModel getLocationByUid(String id) {
		User currentUser = userService.getCurrentUser();
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			Criteria filterCriteria = new Criteria();
			filterCriteria.and("cpo_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i");
			filterCriteria.and("cpo_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i");
			query.addCriteria(filterCriteria);
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {

			Criteria filterCriteria = new Criteria();
			filterCriteria.and("emsp_party_id").regex(".*" + currentUser.getParty_id() + ".*", "i");
			filterCriteria.and("emsp_country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i");
			query.addCriteria(filterCriteria);
		}
		List<CPOLocationModel> find = mongoTemplate.find(query, CPOLocationModel.class);
		if (find.size() > 0) {
			return find.get(0);
		}
		return null;

	}

	@Override
	public List<Map<String, Object>> getCountInformation() {

		User currentUser = userService.getCurrentUser();
		Criteria roleCriteria = new Criteria();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			roleCriteria = Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
					.is(currentUser.getCountry_code());
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {

			Optional<CPOHUBEMSPPermission> permission = cpohubemspPermissionRepository
					.findByEmspPartyId(currentUser.getParty_id());

			if (permission.isPresent())
				roleCriteria = Criteria.where("cpo_party_id").in(permission.get().getCpo_partyIds());
/*  */
		}

		Aggregation aggregationCDR = Aggregation.newAggregation(Aggregation.match(roleCriteria),
				Aggregation.project().and("total_cost.incl_vat").as("inclVat").and("total_energy").as("totalEnergy"),
				Aggregation.group().sum("inclVat").as("totalCost").sum("totalEnergy").as("totalEnergy").count()
						.as("totalCount"));

		Aggregation aggregationSession = Aggregation.newAggregation(Aggregation.match(roleCriteria),
				Aggregation.match(Criteria.where("status").regex("(?i)^ACTIVE$")),
				Aggregation.group("status").count().as("count"), Aggregation.project("count"));

		AggregationResults<Map> totalCostResult = mongoTemplate.aggregate(aggregationCDR, "ocpi_cpo_cdr", Map.class);
		AggregationResults<Map> activeTransaction = mongoTemplate.aggregate(aggregationSession, "ocpi_cpo_session",
				Map.class);

		List<Map<String, Object>> countDetails = getCount();
		List<Map<String, Object>> ls = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();

		// Handling possible empty results
		Map<String, Object> totalCostMap = totalCostResult.getMappedResults().isEmpty() ? new HashMap<>()
				: totalCostResult.getMappedResults().get(0);
		Map<String, Object> activeTransactionMap = activeTransaction.getMappedResults().isEmpty() ? new HashMap<>()
				: activeTransaction.getMappedResults().get(0);
		Map<String, Object> data = countDetails.isEmpty() ? new HashMap<>() : countDetails.get(0);

		// Using Optional.ofNullable() to default to 0
		result.put("totalEvse", Optional.ofNullable(data.get("totalPort")).orElse(0));
		result.put("activeEvse", Optional.ofNullable(data.get("AVAILABLE")).orElse(0));

		result.put("totalLocation", mongoTemplate.count(new Query(), "ocpi_cpo_location"));

		result.put("totalEnergy", Optional.ofNullable(totalCostMap.get("totalEnergy")).orElse(0.0));
		result.put("totalCost", String.format("%.2f", Optional.ofNullable(totalCostMap.get("totalCost")).orElse(0.0)));
		result.put("totalTransaction", Optional.ofNullable(totalCostMap.get("totalCount")).orElse(0));

		result.put("activeTransaction", Optional.ofNullable(activeTransactionMap.get("count")).orElse(0));

		result.put("portStatus", getPie());
		result.put("locationParty", getLocationCountByParty());

		ls.add(result);
		return ls;
	}

	public List<Map> getLocationCountByParty() {

		User currentUser = userService.getCurrentUser();
		Criteria roleCriteria = new Criteria();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			roleCriteria = Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
					.is(currentUser.getCountry_code());
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {

			Optional<CPOHUBEMSPPermission> permission = cpohubemspPermissionRepository
					.findByEmspPartyId(currentUser.getParty_id());

			if (permission.isPresent())
				roleCriteria = Criteria.where("cpo_party_id").in(permission.get().getCpo_partyIds());

		}

		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(roleCriteria),
				Aggregation.group("cpo_party_id").count().as("count"),
				Aggregation.project("count").and("party").previousOperation() // Include 'party' in the output
		);

		// Execute the aggregation
		AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "ocpi_cpo_location", Map.class);

		return results.getMappedResults();
	}

	public List<Map<String, Object>> getPie() {
		List<Map<String, Object>> resultList = new ArrayList<>();

		User currentUser = userService.getCurrentUser();
		Criteria roleCriteria = new Criteria();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			roleCriteria = Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
					.is(currentUser.getCountry_code());
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {

			Optional<CPOHUBEMSPPermission> permission = cpohubemspPermissionRepository
					.findByEmspPartyId(currentUser.getParty_id());

			if (permission.isPresent())
				roleCriteria = Criteria.where("cpo_party_id").in(permission.get().getCpo_partyIds());

		}

		try {
			// Build the aggregation pipeline
			Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(roleCriteria),
					Aggregation.group("status").count().as("count"),
					Aggregation.project("count").and("_id").as("status").andExclude("_id"));
			// Execute the aggregation
			AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "ocpi_cpo_evse", Map.class);

			// Initialize result map and totalCount variable
			Map<String, Integer> result = new HashMap<>();

			// Validate the aggregation results and process
			if (results != null && results.getMappedResults() != null) {
				// Iterate over the aggregation results to build the map
				for (Map<String, Object> record : results.getMappedResults()) {
					String status = (String) record.get("status");
					if (status != null) {
						int count = (int) record.getOrDefault("count", 0);
						result.put(status, count);
					}
				}
			}

			// Map keys to labels with insertion order preservation
			Map<String, String> keyToLabelMap = createStatusLabelMap();

			// Map labels to colors
			Map<String, String> labelToColorMap = createLabelColorMap();

			// Prepare series, labels, and colors
			List<Integer> series = new ArrayList<>();
			List<String> labels = new ArrayList<>();
			List<String> colors = new ArrayList<>();

			// Populate the series, labels, and colors in the same order as keyToLabelMap
			for (String key : keyToLabelMap.keySet()) {
				String label = keyToLabelMap.get(key);
				int count = result.getOrDefault(key, 0); // Default to zero if key is missing

				series.add(count);
				labels.add(label);
				colors.add(labelToColorMap.getOrDefault(label, "#000000")); // Default to black if color not found
			}

			// Prepare the final response
			Map<String, Object> rr = new HashMap<>();
			rr.put("series", series);
			rr.put("labels", labels);
			rr.put("colors", colors);

			resultList.add(rr);

		} catch (Exception e) {

			resultList.add(Collections.singletonMap("error", "Failed to fetch data"));
		}

		return resultList;
	}

	private Map<String, String> createStatusLabelMap() {
		Map<String, String> statusLabelMap = new HashMap<>();
		statusLabelMap.put("AVAILABLE", "Available");
		statusLabelMap.put("INOPERATIVE", "Inoperative");
		statusLabelMap.put("CHARGING", "Charging");
		statusLabelMap.put("REMOVED", "Removed");
		statusLabelMap.put("BLOCKED", "Blocked");
		statusLabelMap.put("OUTOFORDER", "Out of Order");
		statusLabelMap.put("PLANNED", "Planned");
		statusLabelMap.put("RESERVED", "Reserved");
		statusLabelMap.put("UNKNOWN", "Unknown");
		return statusLabelMap;
	}

	private Map<String, String> createLabelColorMap() {
		Map<String, String> labelColorMap = new HashMap<>();
		labelColorMap.put("Available", "#16e31e");
		labelColorMap.put("Inoperative", "#9e9e9e");
		labelColorMap.put("Charging", "#f2bf2b");
		labelColorMap.put("Removed", "#a1887f");
		labelColorMap.put("Blocked", "#ff0000");
		labelColorMap.put("Out of Order", "#fafdf4");
		labelColorMap.put("Planned", "#40c4ff");
		labelColorMap.put("Reserved", "#e040fb");
		labelColorMap.put("Unknown", "#800000");
		return labelColorMap;
	}

}
