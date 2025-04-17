package com.evgateway.cpohubserver.services;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.evgateway.cpohubserver.Utils.Utils;
import com.evgateway.cpohubserver.cnum.ERole;
import com.evgateway.cpohubserver.model.CPOHUBEMSPPermission;
import com.evgateway.cpohubserver.model.CPOHUBPartnerModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.repository.CPOHUBEMSPPermissionRepository;
import com.evgateway.cpohubserver.request.StatusCount;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private MongoTemplate mongoTemplate;



	@Autowired
	private CPOHUBEMSPPermissionRepository cpohubemspPermissionRepository;

	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServiceImpl.class);

	@Override
	public List<Map<String, Object>> getDashboardReports(int id, int period, String type) {

		LOGGER.info("DashboardServiceImpl.getDashboardReports() -  [" + type + "] with " + period);

		List<Map<String, Object>> finalData = new ArrayList<Map<String, Object>>();

		switch (id) {

		case 1:
			finalData = getAllCount();
			break;
		case 2:
			finalData = getPie();
			break;
		case 3:
			finalData = getRecentTransaction();
			break;
		case 4:
			finalData = getReportSession(period, type);
			break;
		case 5:
			finalData = getReportClientBased(period, type);
			break;
		default:
			break;
		}
		return finalData;
	}

	public List<Map<String, Object>> getAllCount() {
		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();

		Criteria roleCriteria = new Criteria();
		User currentUser = userService.getCurrentUser();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			roleCriteria = Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
					.is(currentUser.getCountry_code());
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			Optional<CPOHUBEMSPPermission> permission = cpohubemspPermissionRepository
					.findByEmspPartyId(currentUser.getParty_id());

			if (permission.isPresent())
				roleCriteria = Criteria.where("cpo_party_id").in(permission.get().getCpo_partyIds());
			
			
			
			
//			roleCriteria = Criteria.where("emsp_party_id").is(currentUser.getParty_id()).and("emsp_country_code")
//					.is(currentUser.getCountry_code());
		}

		try {
			// Aggregation for active count
			long activeStatusCount = getActiveCount(roleCriteria);

			Instant now = Instant.now();
			LocalDate currentDate = now.atZone(ZoneOffset.UTC).toLocalDate();
			int currentYear = currentDate.getYear();
			LocalDate startOfYear = LocalDate.of(currentYear, 1, 1);
			Instant startInstant = startOfYear.atStartOfDay(ZoneOffset.UTC).toInstant();

			// Populate the result map with calculated counts
			result.put("activeTransaction", activeStatusCount);
			result.put("activePartner", getStatusCount("ONLINE", roleCriteria));
			result.put("inActivePartner", getStatusCount("OFFLINE", roleCriteria));
			result.put("systemUpTime",

					Double.parseDouble(String.format("%.2f", calculateOverallSystemUptime(startInstant, now))));
			System.err.println("result 3");
			result.put("totalPartner", getCount("ocpi_cpohub_partner"));
			result.put("totalDataTransferred",
					Double.parseDouble(String.format("%.2f", convertBytesToMB(calculateOverallDataTransfer()))));
			result.put("totalTransaction", getCount("ocpi_cpo_cdr"));
			result.put("totalLocation", getCount("ocpi_cpo_location"));
			result.put("totalPort", getCount("ocpi_cpo_evse"));
			result.put("locations", getLocationCountByParty());
			result.put("activePorts", getAvailableEVSE(roleCriteria));

			System.err.println("result final"+result.toString());
			// Add the result to the final list
			resultList.add(result);

		} catch (Exception e) {
			// Log the error and return an empty result if something goes wrong
			LOGGER.error("Error occurred while fetching the counts: ", e);
			result.put("error", "Failed to fetch counts");
			resultList.add(result);
		}

		return resultList;
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

	public long getStatusCount(String status, Criteria roleCriteria) {
		// Create the aggregation pipeline
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(roleCriteria),
				Aggregation.match(Criteria.where("status").is(status)), // Filter
																		// by
																		// status
				Aggregation.count().as("count") // Count the matching documents
		);

		// Execute the aggregation and return the count
		AggregationResults<StatusCount> results = mongoTemplate.aggregate(aggregation, "ocpi_cpohub_partner",
				StatusCount.class);
		return results.getMappedResults().stream().findFirst().map(StatusCount::getCount).orElse(0L); // Return 0 if no
																										// result
	}

	// Helper method to get active count
	private long getActiveCount(Criteria roleCriteria) {

		Aggregation activeCountAggregation = Aggregation.newAggregation(Aggregation.match(roleCriteria),
				Aggregation.match(Criteria.where("status").regex("(?i)^active$")),
				Aggregation.group().count().as("count"));

		AggregationResults<Map> activeCounts = mongoTemplate.aggregate(activeCountAggregation,
				"ocpi_cpo_session_activity", Map.class);
		Map<String, Object> countResultAct = activeCounts.getUniqueMappedResult();

		return (countResultAct != null && countResultAct.containsKey("count"))
				? ((Number) countResultAct.get("count")).longValue()
				: 0;
	}

	private long getAvailableEVSE(Criteria roleCriteria) {
		Aggregation activeCountAggregation = Aggregation.newAggregation(Aggregation.match(roleCriteria),
				Aggregation.match(Criteria.where("status").regex("(?i)^AVAILABLE$")),
				Aggregation.group().count().as("count"));

		AggregationResults<Map> activeCounts = mongoTemplate.aggregate(activeCountAggregation, "ocpi_cpo_evse",
				Map.class);

		Map<String, Object> countResultAct = activeCounts.getUniqueMappedResult();

		return (countResultAct != null && countResultAct.containsKey("count"))
				? ((Number) countResultAct.get("count")).longValue()
				: 0;
	}

	// Helper method to get count for a collection
	private long getCount(String collectionName) {
		Criteria roleCriteria = new Criteria();
		User currentUser = userService.getCurrentUser();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			roleCriteria = Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
					.is(currentUser.getCountry_code());
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			Optional<CPOHUBEMSPPermission> permission = cpohubemspPermissionRepository
					.findByEmspPartyId(currentUser.getParty_id());

			if (permission.isPresent())
				roleCriteria = Criteria.where("cpo_party_id").in(permission.get().getCpo_partyIds());
			
			
			
//			roleCriteria = Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
//					.is(currentUser.getCountry_code());
		}
		return mongoTemplate.count(Query.query(roleCriteria), collectionName);
	}

	public List<Map<String, Object>> getRecentTransaction() {
		List<Map<String, Object>> resultList = new ArrayList<>();
		User currentUser = userService.getCurrentUser();
		Criteria roleCriteria = new Criteria();

		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			roleCriteria = Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
					.is(currentUser.getCountry_code());
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			roleCriteria = Criteria.where("emsp_party_id").is(currentUser.getParty_id()).and("emsp_country_code")
					.is(currentUser.getCountry_code());
		}

		try {
			// Build the aggregation pipeline with role-based filtering
			Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(roleCriteria), // Filter data based
																									// on the role
					Aggregation.sort(Sort.by(Sort.Order.desc("last_updated"))), // Sort by 'last_updated' in descending
																				// order
					Aggregation.limit(10) // Limit to the latest 10 documents
			);

			// Execute the aggregation query on the 'ocpi_cpo_cdr' collection
			AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class);

			// If results are present, process and return them
			if (results != null && results.getMappedResults() != null) {
				resultList = results.getMappedResults().stream().map(map -> {
					Map<String, Object> modifiedMap = new HashMap<>(map); // Create a copy to avoid modifying original
																			// map
					if (modifiedMap.containsKey("total_time")) {
						modifiedMap.put("total_time", Utils
								.getTimeFormate(Double.parseDouble(String.valueOf(modifiedMap.get("total_time")))));
					}
					return modifiedMap;
				}).collect(Collectors.toList());
			}
		} catch (Exception e) {
			// Log the error for debugging
			LOGGER.error("Error occurred while fetching recent transactions: ", e);
			// Return an error message
			resultList.add(Collections.singletonMap("error", "Failed to fetch recent transactions"));
		}

		return resultList;
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
					Aggregation.group("status") // Group by 'status'
							.count().as("count"), // Count documents in each group
					Aggregation.project("count") // Keep 'count' and also include '_id' field
							.and("_id").as("status") // Rename '_id' to 'status'
							.andExclude("_id") // Exclude '_id' from the result
			);
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

			// Add the result to the list
			resultList.add(rr);

		} catch (Exception e) {
			// Log the error and handle gracefully
			LOGGER.error("Error occurred while fetching pie chart data: ", e);

			// Optionally, return an empty result or a default response
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

	public List<Map<String, Object>> fetchYearlyReport() {
		try {
			int currentYear = Year.now().getValue();

			List<String> monthNames = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
					"August", "September", "October", "November", "December");

			List<Map<String, Object>> allMonths = new ArrayList<>();
			for (int i = 0; i < 12; i++) {
				Map<String, Object> monthData = new HashMap<>();
				monthData.put("month", i + 1);
				monthData.put("monthName", monthNames.get(i));
				monthData.put("count", 0);
				allMonths.add(monthData);
			}
			User currentUser = userService.getCurrentUser();
			Criteria roleCriteria = new Criteria();

			if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
				roleCriteria = Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
						.is(currentUser.getCountry_code());
			} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
				roleCriteria = Criteria.where("emsp_party_id").is(currentUser.getParty_id()).and("emsp_country_code")
						.is(currentUser.getCountry_code());
			}
			// Define the aggregation pipeline with role-based filtering
			Aggregation aggregation = Aggregation.newAggregation(
					// Match documents based on role-based criteria and filter for the current year
					Aggregation.match(new Criteria().andOperator(roleCriteria,
							Criteria.where("end_date_time").gte(LocalDate.of(currentYear, 1, 1))
									.lt(LocalDate.of(currentYear + 1, 1, 1)))),

					// Extract the year and month from 'end_date_time'
					Aggregation.project("end_date_time").andExpression("year(end_date_time)").as("year")
							.andExpression("month(end_date_time)").as("month"),

					// Group by year and month, count the number of occurrences
					Aggregation.group("year", "month").count().as("count"),

					// Project the data back with clearer month names
					Aggregation.project().and("_id.year").as("year").and("_id.month").as("month")
							.and(ArrayOperators.ArrayElemAt.arrayOf(monthNames)
									.elementAt(ArithmeticOperators.Subtract.valueOf("_id.month").subtract(1)))
							.as("monthName").and("count").as("count"),

					Aggregation.sort(Sort.by(Sort.Order.asc("year"), Sort.Order.asc("month"))));

			// Execute the aggregation
			AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class);
			// Create a map to hold the months by year
			Map<Integer, Map<Integer, Map<String, Object>>> yearMonthMap = new HashMap<>();

			// Populate the map with actual aggregation data
			for (Map<String, Object> data : result) {
				Integer year = (Integer) data.get("year");
				Integer month = (Integer) data.get("month");
				String monthName = (String) data.get("monthName");
				Integer count = (Integer) data.get("count");

				yearMonthMap.computeIfAbsent(year, k -> new HashMap<>()).put(month, new HashMap<String, Object>() {
					{
						put("monthName", monthName);
						put("count", count);
					}
				});
			}

			for (Map<Integer, Map<String, Object>> months : yearMonthMap.values()) {
				for (int j = 1; j <= 12; j++) {
					// Check if the month is absent in the map, then add it with default values
					months.putIfAbsent(j, new HashMap<String, Object>());
					Map<String, Object> monthData = months.get(j);
					// Set default values for monthName and count if not already set
					monthData.putIfAbsent("monthName", monthNames.get(j - 1)); // Get the month name
					monthData.putIfAbsent("count", 0); // Default count to 0
				}
			}

			// Convert the map back into the desired list format
			List<Map<String, Object>> finalResults = new ArrayList<>();
			for (Map<Integer, Map<String, Object>> months : yearMonthMap.values()) {
				for (int month = 1; month <= 12; month++) {
					Map<String, Object> data = months.get(month);
					finalResults.add(new HashMap<String, Object>() {
						{
							put("time", data.get("monthName"));
							put("value", data.get("count"));
						}
					});
				}
			}

			return finalResults;

		} catch (Exception e) {
			// Log the error and rethrow an exception
//			e.printStackTrace();
			e.getMessage();
			throw new RuntimeException("An error occurred while fetching the yearly report.", e);
		}
	}

	public List<Map<String, Object>> fetchMonthlyReport() {
		try {
			int currentYear = Year.now().getValue();
			int currentMonth = LocalDate.now().getMonthValue();

			// Determine the number of days in the current month
			YearMonth yearMonth = YearMonth.of(currentYear, currentMonth);
			int daysInMonth = yearMonth.lengthOfMonth();

			// Generate a list of dates for the current month
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			List<Map<String, Object>> allDays = new ArrayList<>();
			for (int i = 1; i <= daysInMonth; i++) {
				Map<String, Object> dayData = new HashMap<>();
				LocalDate date = LocalDate.of(currentYear, currentMonth, i);
				dayData.put("date", date.format(formatter)); // Format as dd/MM/yyyy
				dayData.put("value", 0); // Default value is 0
				allDays.add(dayData);
			}

			// Define the aggregation pipeline
			Aggregation aggregation = Aggregation.newAggregation(
					// Match documents for the current month
					Aggregation.match(Criteria.where("end_date_time").gte(LocalDate.of(currentYear, currentMonth, 1))
							.lt(LocalDate.of(currentYear, currentMonth, daysInMonth).plusDays(1))),

					// Extract the year, month, and day from 'end_date_time'
					Aggregation.project("end_date_time").andExpression("year(end_date_time)").as("year")
							.andExpression("month(end_date_time)").as("month")
							.andExpression("dayOfMonth(end_date_time)").as("day"),

					// Group by year, month, and day, count the number of occurrences
					Aggregation.group("year", "month", "day").count().as("value"),

					// Project the data back with a clearer format
					Aggregation.project().and("_id.year").as("year").and("_id.month").as("month").and("_id.day")
							.as("day").and("value").as("value"),

					// Sort by day
					Aggregation.sort(Sort.by(Sort.Order.asc("day"))));

			// Execute the aggregation
			AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class);

			// Map the aggregation results for easier lookup
			Map<String, Integer> dateValueMap = new HashMap<>();
			for (Map<String, Object> data : result) {
				Integer day = (Integer) data.get("day");
				LocalDate date = LocalDate.of(currentYear, currentMonth, day);
				String formattedDate = date.format(formatter);
				Integer value = (Integer) data.get("value");
				dateValueMap.put(formattedDate, value);
			}

			// Ensure all dates are present in the result
			List<Map<String, Object>> finalResults = new ArrayList<>();
			for (int i = 1; i <= daysInMonth; i++) {
				Map<String, Object> dayData = new HashMap<>();
				LocalDate date = LocalDate.of(currentYear, currentMonth, i);
				String formattedDate = date.format(formatter);
				dayData.put("time", formattedDate); // Formatted as dd/MM/yyyy
				dayData.put("value", dateValueMap.getOrDefault(formattedDate, 0)); // Default to 0 if no data
				finalResults.add(dayData);
			}

			return finalResults;
		} catch (Exception e) {
			// Log the error and rethrow an exception
//			e.printStackTrace();
			e.getMessage();
			throw new RuntimeException("An error occurred while fetching the monthly report.", e);
		}
	}

	public List<Map<String, Object>> fetchWeeklyReport() {
		try {
			LocalDate currentDate = LocalDate.now();

			// Determine the start (Monday) and end (Sunday) of the current week
			LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
			LocalDate endOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

			// Day names for the week
			List<String> daysOfWeek = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
					"Saturday");

			// Aggregation pipeline
			Aggregation aggregation = Aggregation.newAggregation(
					// Match documents for the current week (between startOfWeek and endOfWeek)
					Aggregation.match(Criteria.where("end_date_time").gte(startOfWeek).lte(endOfWeek)),

					// Extract the day of the week from 'end_date_time'
					Aggregation.project("end_date_time").andExpression("dayOfWeek(end_date_time)").as("dayOfWeek"),

					// Group by dayOfWeek and count the number of occurrences
					Aggregation.group("dayOfWeek").count().as("count"),

					// Convert the dayOfWeek to a human-readable format
					Aggregation.project().and("_id").as("Day").and("count").as("value"),

					// Sort by Day (Sunday to Saturday)
					Aggregation.sort(Sort.by(Sort.Order.asc("Day"))));

			// Execute the aggregation
			AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class);

			// Prepare default data for each day of the week (Sunday to Saturday)
			List<Map<String, Object>> finalResults = new ArrayList<>();
			for (int i = 0; i < 7; i++) { // Days of the week from Sunday (0) to Saturday (6)
				Map<String, Object> defaultData = new HashMap<>();
				defaultData.put("time", daysOfWeek.get(i)); // Day of the week
				defaultData.put("value", 0); // Default count as 0
				finalResults.add(defaultData);
			}

			// Update the results with actual counts
			for (Map<String, Object> data : result) {
				Integer dayOfWeek = (Integer) data.get("Day");
				Integer value = (Integer) data.get("value");

				// Adjust for the 1-based index from MongoDB and update corresponding day
				finalResults.get(dayOfWeek - 1).put("value", value); // Adjust for 1-based index (1 = Sunday)
			}

			// Return the finalResults list
			return finalResults;
		} catch (Exception e) {
//			e.printStackTrace();
			e.getMessage();
			throw new RuntimeException("An error occurred while fetching the weekly report.", e);
		}
	}

	public List<Map<String, Object>> fetchDailyReport() {
		try {
			LocalDate currentDate = LocalDate.now();
			LocalDateTime startOfDay = currentDate.atStartOfDay();
			LocalDateTime endOfDay = currentDate.atTime(23, 59, 59);

			// Define the hourly intervals for the current day
			List<String> hours = new ArrayList<>();
			for (int i = 0; i < 24; i++) {
				hours.add(String.format("%02d:00", i)); // Hourly format like "00:00", "01:00", ..., "23:00"
			}

			// Define the aggregation pipeline
			Aggregation aggregation = Aggregation.newAggregation(
					// Match documents for the current day (between startOfDay and endOfDay)
					Aggregation.match(Criteria.where("end_date_time").gte(startOfDay).lte(endOfDay)),

					// Extract the hour from 'end_date_time' (this will group records by the hour)
					Aggregation.project("end_date_time").andExpression("hour(end_date_time)").as("hour"),

					// Group by hour and count the number of occurrences
					Aggregation.group("hour").count().as("value"),

					// Sort by hour
					Aggregation.sort(Sort.by(Sort.Order.asc("hour"))));

			// Execute the aggregation
			AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class);

			// Map the aggregation results for easier lookup by hour
			Map<Integer, Integer> hourValueMap = new HashMap<>();
			for (Map<String, Object> data : result) {
				Integer hour = (Integer) data.get("_id");
				Integer value = (Integer) data.get("value");
				hourValueMap.put(hour, value);
			}

			// Prepare the final results by adding all hours (00:00 to 23:00)
			List<Map<String, Object>> finalResults = new ArrayList<>();
			DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm 'hrs'");

			for (int i = 0; i < 24; i++) {
				Map<String, Object> hourData = new HashMap<>();
				LocalTime hour = LocalTime.of(i, 0); // Convert hour to LocalTime (e.g., 00:00, 01:00, ...)
				hourData.put("time", hour.format(hourFormatter)); // Format time like "00:00 hrs"
				hourData.put("value", hourValueMap.getOrDefault(i, 0)); // Default to 0 if no data for the hour
				finalResults.add(hourData);
			}

			return finalResults;
		} catch (Exception e) {
			// Handle exceptions and log errors
//			e.printStackTrace();
			e.getMessage();
			throw new RuntimeException("An error occurred while fetching the daily report.", e);
		}
	}

	public List<Map<String, Object>> getClientReportYearly() {

		List<Map<String, Object>> finalResults = new ArrayList<>();
//		if (currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {
		try {
			int currentYear = Year.now().getValue();
			List<String> monthNames = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
					"August", "September", "October", "November", "December");

			// Aggregation pipeline
			Aggregation aggregation = Aggregation.newAggregation(
					// Match documents for the current year
					Aggregation.match(Criteria.where("end_date_time").gte(LocalDate.of(currentYear, 1, 1))
							.lt(LocalDate.of(currentYear + 1, 1, 1))),

					// Extract the year, month, and cpo_party_id from 'end_date_time'
					Aggregation.project("end_date_time", "cpo_party_id").andExpression("year(end_date_time)").as("year")
							.andExpression("month(end_date_time)").as("month"),

					// Group by year, month, and cpo_party_id, count occurrences
					Aggregation.group("year", "month", "cpo_party_id").count().as("count"),

					// Convert the month number to a month name
					Aggregation.project().and("_id.year").as("year").and("_id.month").as("month")
							.and("_id.cpo_party_id").as("cpo_party_id")
							.and(ArrayOperators.ArrayElemAt.arrayOf(monthNames)
									.elementAt(ArithmeticOperators.Subtract.valueOf("_id.month").subtract(1)))
							.as("monthName").and("count").as("count"),

					Aggregation.sort(
							Sort.by(Sort.Order.asc("year"), Sort.Order.asc("month"), Sort.Order.asc("cpo_party_id"))));

			// Execute the aggregation
			AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class);

			// Prepare default data for each month for every cpo_party_id
			Set<String> cpoPartyIds = result.getMappedResults().stream().map(data -> (String) data.get("cpo_party_id"))
					.collect(Collectors.toSet());

			// Create results for all months (January to December) and all cpo_party_ids

			for (String cpoPartyId : cpoPartyIds) {
				for (int i = 0; i < 12; i++) { // Loop through all months (January to December)
					Map<String, Object> defaultData = new LinkedHashMap<>();
					defaultData.put("time", monthNames.get(i));
					defaultData.put("partyId", cpoPartyId);
					defaultData.put("value", 0); // Default value 0
					finalResults.add(defaultData);
				}
			}

			// Update the results with actual counts from the aggregation
			for (Map<String, Object> data : result.getMappedResults()) {
				String monthName = (String) data.get("monthName");
				String cpoPartyId = (String) data.get("cpo_party_id");
				Integer value = (Integer) data.get("count");

				// Update the corresponding record in finalResults
				for (Map<String, Object> record : finalResults) {
					if (record.get("time").equals(monthName) && record.get("partyId").equals(cpoPartyId)) {
						record.put("value", value);
						break;
					}
				}
			}

			return finalResults;
		} catch (Exception e) {
			// Handle exceptions and log errors
//			e.printStackTrace();
			e.getMessage();
			throw new RuntimeException("An error occurred while generating the yearly client report.", e);
		}
//		} else
//			return null;
	}

	public List<Map<String, Object>> getClientReportMonthly() {
		try {
			// Determine the current year, month, and days in the current month
			YearMonth yearMonth = YearMonth.now();
			int currentYear = yearMonth.getYear();
			int currentMonth = yearMonth.getMonthValue();
			int daysInMonth = yearMonth.lengthOfMonth();

			// Define the start and end dates for the current month
			LocalDate startDate = LocalDate.of(currentYear, currentMonth, 1);
			LocalDate endDate = startDate.plusMonths(1); // Exclusive

			// Aggregation pipeline
			Aggregation aggregation = Aggregation.newAggregation(
					// Match documents for the current month
					Aggregation.match(Criteria.where("end_date_time").gte(startDate).lt(endDate)),

					// Extract year, month, day, and cpo_party_id
					Aggregation.project("end_date_time", "cpo_party_id").andExpression("dayOfMonth(end_date_time)")
							.as("day"),

					// Group by day and cpo_party_id and count occurrences
					Aggregation.group("day", "cpo_party_id").count().as("count"),

					// Project the results with readable fields
					Aggregation.project().and("_id.day").as("Day").and("_id.cpo_party_id").as("partyId").and("count")
							.as("value"),

					// Sort by day and cpo_party_id
					Aggregation.sort(Sort.by(Sort.Order.asc("Day"), Sort.Order.asc("partyId"))));

			// Execute the aggregation query
			AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class);

			// Extract unique cpo_party_ids from the result
			Set<String> cpoPartyIds = result.getMappedResults().stream().map(data -> (String) data.get("partyId"))
					.collect(Collectors.toSet());

			// Prepare default data for every day in the current month for each cpo_party_id
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			List<Map<String, Object>> finalResults = new ArrayList<>();
			for (String cpoPartyId : cpoPartyIds) {
				for (int day = 1; day <= daysInMonth; day++) {
					Map<String, Object> defaultData = new LinkedHashMap<>();
					LocalDate date = LocalDate.of(currentYear, currentMonth, day);
					defaultData.put("time", date.format(dateFormatter));
					defaultData.put("partyId", cpoPartyId);
					defaultData.put("value", 0);
					finalResults.add(defaultData);
				}
			}

			// Update default results with actual counts from the aggregation query
			for (Map<String, Object> data : result.getMappedResults()) {
				Integer day = (Integer) data.get("Day");
				String cpoPartyId = (String) data.get("partyId");
				Integer value = (Integer) data.get("value");

				LocalDate date = LocalDate.of(currentYear, currentMonth, day);
				for (Map<String, Object> record : finalResults) {
					if (record.get("time").equals(date.format(dateFormatter))
							&& record.get("partyId").equals(cpoPartyId)) {
						record.put("value", value);
						break;
					}
				}
			}

			// Return the final results
			return finalResults;
		} catch (Exception e) {
			// Handle exceptions and log errors
//			e.printStackTrace();
			e.getMessage();
			throw new RuntimeException("An error occurred while generating the monthly client report.", e);
		}
	}

	public List<Map<String, Object>> getClientReportWeekly() {
		try {
			// Determine the start (Monday) and end (Sunday) of the current week
			LocalDate currentDate = LocalDate.now();
			LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
			LocalDate endOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

			Map<Integer, String> dayOfWeekMap = new HashMap<>();
			dayOfWeekMap.put(1, "Monday");
			dayOfWeekMap.put(2, "Tuesday");
			dayOfWeekMap.put(3, "Wednesday");
			dayOfWeekMap.put(4, "Thursday");
			dayOfWeekMap.put(5, "Friday");
			dayOfWeekMap.put(6, "Saturday");
			dayOfWeekMap.put(7, "Sunday");

			// Fetch distinct CPO party IDs
			List<String> cpoPartyIdList = mongoTemplate.findDistinct(new Query(), "cpo_party_id", "ocpi_cpo_cdr",
					String.class);

			// Aggregation pipeline
			Aggregation aggregation = Aggregation.newAggregation(
					// Match documents for the current week
					Aggregation.match(Criteria.where("end_date_time").gte(startOfWeek.atStartOfDay())
							.lte(endOfWeek.atTime(LocalTime.MAX))),

					// Extract the day of the week and CPO party ID
					Aggregation.project("end_date_time", "cpo_party_id").andExpression("dayOfWeek(end_date_time)")
							.as("dayOfWeek"),

					// Group by dayOfWeek and cpo_party_id, count occurrences
					Aggregation.group("dayOfWeek", "cpo_party_id").count().as("count"),

					// Project the result
					Aggregation.project().and("_id.dayOfWeek").as("dayOfWeek").and("_id.cpo_party_id")
							.as("cpo_party_id").and("count").as("count"),

					// Sort by dayOfWeek and cpo_party_id
					Aggregation.sort(Sort.by(Sort.Order.asc("dayOfWeek"), Sort.Order.asc("cpo_party_id"))));

			// Execute aggregation
			AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class);

			// Default results for all days and CPOs
			List<Map<String, Object>> defaultResults = cpoPartyIdList.stream()
					.flatMap(cpoPartyId -> dayOfWeekMap.entrySet().stream().map(entry -> {
						Map<String, Object> defaultData = new LinkedHashMap<>();
						defaultData.put("time", entry.getValue()); // Day of the week
						defaultData.put("partyId", cpoPartyId);
						defaultData.put("value", 0); // Default count
						return defaultData;
					})).collect(Collectors.toList());

			// Create a map for efficient lookup
			Map<String, Map<String, Object>> resultMap = defaultResults.stream().collect(
					Collectors.toMap(record -> record.get("time") + "_" + record.get("partyId"), record -> record));

			// Update default results with actual counts
			for (Map<String, Object> data : results.getMappedResults()) {
				Integer dayOfWeek = (Integer) data.get("dayOfWeek");
				String cpoPartyId = (String) data.get("cpo_party_id");
				Integer count = (Integer) data.get("count");

				if (dayOfWeek != null && cpoPartyId != null && count != null) {
					String key = dayOfWeekMap.get(dayOfWeek) + "_" + cpoPartyId;
					Map<String, Object> record = resultMap.get(key);
					if (record != null) {
						record.put("value", count);
					}
				}
			}

			// Return the final results
			return new ArrayList<>(resultMap.values());
		} catch (Exception e) {
			// Log the error and return an empty list in case of failure
//			System.err.println("Error fetching weekly client report: " + e.getMessage());
//			e.printStackTrace();
			e.getMessage();
			return Collections.emptyList();
		}
	}

	public List<Map<String, Object>> getClientReportDaily() {
		try {
			// Define today's date range
			LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
			LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

			// Fetch distinct CPO party IDs
			List<String> cpoPartyIdList = mongoTemplate.findDistinct(new Query(), "cpo_party_id", "ocpi_cpo_cdr",
					String.class);

			if (cpoPartyIdList.isEmpty()) {
				return Collections.emptyList(); // No CPOs found
			}

			// Aggregation pipeline
			Aggregation aggregation = Aggregation.newAggregation(
					Aggregation.match(Criteria.where("end_date_time").gte(startOfDay).lte(endOfDay)), // Match today's
																										// records
					Aggregation.project("end_date_time", "cpo_party_id").andExpression("hour(end_date_time)")
							.as("Hour"), // Extract hour
					Aggregation.group("Hour", "cpo_party_id").count().as("count"), // Group by hour and CPO
					Aggregation.project().and("_id.Hour").as("Hour").and("_id.cpo_party_id").as("cpo_party_id")
							.and("count").as("count"), // Format fields
					Aggregation.sort(Sort.by(Sort.Order.asc("Hour"), Sort.Order.asc("cpo_party_id"))) // Sort
			);

			// Execute aggregation
			AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "ocpi_cpo_cdr", Map.class);

			// Prepare default results for all hours (00:00 to 23:00) for each CPO
			List<Map<String, Object>> finalResults = generateDefaultData(cpoPartyIdList);

			// Update default results with actual counts
			updateResultsWithData(finalResults, results.getMappedResults());

			return finalResults;

		} catch (Exception e) {
			// Log error and return an empty list
//			e.printStackTrace();
			e.getMessage();
			return Collections.emptyList();
		}
	}

	private List<Map<String, Object>> generateDefaultData(List<String> cpoPartyIdList) {
		DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm 'hrs'");
		List<Map<String, Object>> defaultResults = new ArrayList<>();

		// Generate default results for each hour and CPO
		for (String cpoPartyId : cpoPartyIdList) {
			for (int hour = 0; hour < 24; hour++) {
				Map<String, Object> defaultData = new LinkedHashMap<>();
				defaultData.put("time", LocalTime.of(hour, 0).format(hourFormatter));
				defaultData.put("partyId", cpoPartyId);
				defaultData.put("value", 0); // Default count is 0
				defaultResults.add(defaultData);
			}
		}
		return defaultResults;
	}

	private void updateResultsWithData(List<Map<String, Object>> defaultResults, List<Map> actualResults) {
		DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm 'hrs'");

		// Update default results with actual values
		for (Map<String, Object> actualData : actualResults) {
			Integer hour = (Integer) actualData.get("Hour");
			String cpoPartyId = (String) actualData.get("cpo_party_id");
			Integer count = (Integer) actualData.get("count");

			String formattedHour = LocalTime.of(hour, 0).format(hourFormatter);

			// Find the matching record in the default results and update the value
			defaultResults.stream().filter(
					record -> record.get("time").equals(formattedHour) && record.get("partyId").equals(cpoPartyId))
					.findFirst().ifPresent(record -> record.put("value", count));
		}
	}

	public double calculateOverallSystemUptime(Instant from, Instant to) {

		Criteria roleCriteria = new Criteria();
		User currentUser = userService.getCurrentUser();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			roleCriteria = Criteria.where("party_id").is(currentUser.getParty_id()).and("country_code")
					.is(currentUser.getCountry_code());
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			roleCriteria = Criteria.where("party_id").is(currentUser.getParty_id()).and("country_code")
					.is(currentUser.getCountry_code());
		}

		// Fetch all partners
//		List<CPOHUBPartnerModel> partners = partnerRepository.findAll();
		// Fetch all partners with the filters
		List<CPOHUBPartnerModel> partners = mongoTemplate.find(Query.query(roleCriteria), CPOHUBPartnerModel.class);
		if (partners.isEmpty()) {
			return 100.0; // No partners, assume 100% uptime
		}

		// Total monitoring time for all partners (in seconds)
		long totalMonitoringTimeInSeconds = ChronoUnit.SECONDS.between(from, to) * partners.size();

		// Total downtime for all partners (in seconds)
		long totalDowntimeInSeconds = partners.stream().mapToLong(CPOHUBPartnerModel::getTotal_down_time).sum();
		long totalUptimeInSeconds = totalMonitoringTimeInSeconds - totalDowntimeInSeconds;
		return (double) totalUptimeInSeconds / totalMonitoringTimeInSeconds * 100;
	}

	private long calculateOverallDataTransfer() {

		Criteria roleCriteria = new Criteria();
		User currentUser = userService.getCurrentUser();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			roleCriteria = Criteria.where("party_id").is(currentUser.getParty_id()).and("country_code")
					.is(currentUser.getCountry_code());
		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			roleCriteria = Criteria.where("party_id").is(currentUser.getParty_id()).and("country_code")
					.is(currentUser.getCountry_code());
		}

		// Fetch all partners
//		List<CPOHUBPartnerModel> partners = partnerRepository.findAll();
		// Fetch all partners with the filters
		List<CPOHUBPartnerModel> partners = mongoTemplate.find(Query.query(roleCriteria), CPOHUBPartnerModel.class);
		System.err.println("partners  " + partners.get(0).getTotal_down_time());
		// Fetch all partners
//		List<CPOHUBPartnerModel> partners = partnerRepository.findAll();

		if (partners.isEmpty()) {
			return 0; // No partners, assume 100% uptime
		}
		return partners.stream().mapToLong(CPOHUBPartnerModel::getTotal_data_exchange) // Extract the totalDownTime
																						// field
				.sum(); // Sum all the values
	}

	private double convertBytesToMB(long bytes) {
		return bytes / (1024.0 * 1024.0);
	}

	public List<Map<String, Object>> getReportClientBased(int period, String type) {

		if (period == 1)
			return getClientReportDaily();
		else if (period == 7)
			return getClientReportWeekly();
		else if (period == 30)
			return getClientReportMonthly();
		else if (period == 365)
			return getClientReportYearly();
		else
			return null;

	}

	public List<Map<String, Object>> getReportSession(int period, String type) {
		if (period == 1)
			return fetchDailyReport();
		else if (period == 7)
			return fetchWeeklyReport();
		else if (period == 30)
			return fetchMonthlyReport();
		else if (period == 365)
			return fetchYearlyReport();
		else
			return null;

	}

}