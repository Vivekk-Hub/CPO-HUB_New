package com.evgateway.cpohubserver.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.evgateway.cpohubserver.Utils.Utils;
import com.evgateway.cpohubserver.cnum.ERole;
import com.evgateway.cpohubserver.controllers.PartnerController;
import com.evgateway.cpohubserver.exception.DataNotFoundException;
import com.evgateway.cpohubserver.exception.UserNotFoundException;
import com.evgateway.cpohubserver.model.CPOEndpointModel;
import com.evgateway.cpohubserver.model.CPOHUBDowntimeModel;
import com.evgateway.cpohubserver.model.CPOHUBPartnerModel;
import com.evgateway.cpohubserver.model.CPOPullRequestModel;
import com.evgateway.cpohubserver.model.CPOSchedulerFrequencyModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.repository.CPOEndpointRepository;
import com.evgateway.cpohubserver.repository.CPOSchedulerFrequencyRepository;
import com.evgateway.cpohubserver.repository.PartnerRepository;
import com.evgateway.cpohubserver.request.CPODetails;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.response.CPOSchedularFrequencyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class PartnerServiceImpl implements PartnerService {


	@Autowired
	private CPOEndpointRepository cpoEndpointRepository;

	@Autowired
	private PartnerRepository ocpihubPartnerRepository;

	@Autowired
	private CPOSchedulerFrequencyRepository cpoSchedulerFrequencyRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplateService restTemplateService;

	@Value("${cpohub.url}")
	private String cpohub_url;

	private static final Logger LOGGER = LoggerFactory.getLogger(PartnerController.class);

	@Override
	public PageResult<CPOHUBPartnerModel> getPartnerTableData(int pagesize, int page,
			Map<String, List<String>> filters) {
		Query query = new Query();
		User currentUser = userService.getCurrentUser();
		if (currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {

			// Add additional filters if present
			List<String> values = null;

			if (filters != null && !filters.isEmpty()) {
				List<String> keys = filters.get("key");
				values = filters.get("value");

				if (keys != null && values != null && !keys.isEmpty() && !values.isEmpty()) {
					List<Criteria> orCriteria = new ArrayList<>();

					for (String key : keys) {
						for (String value : values) {
							if (key != null && value != null) {
								// Check if the key is "uid" and convert to ObjectId if valid
								if (key.equals("id")) {
									try {
										orCriteria.add(Criteria.where(key).is(new ObjectId(value.trim())));
									} catch (IllegalArgumentException e) {
										orCriteria
												.add(Criteria.where("idString").regex(".*" + value.trim() + ".*", "i"));
									}
								} else if (key.equals("last_activity")) {
									// Handle date search for 'last_updated'
									try {
										Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
												.parse(value.trim());
										orCriteria.add(Criteria.where(key).gte(date));
									} catch (ParseException e) {
										// Handle invalid date format gracefully

									}
								} else {
									// Default behavior for other keys
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
			query.with(Sort.by(Sort.Order.desc("last_activiity")));
			List<CPOHUBPartnerModel> partner = mongoTemplate.find(query.with(pageable), CPOHUBPartnerModel.class);

			long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOHUBPartnerModel.class);

			Page<CPOHUBPartnerModel> partners = new PageImpl<>(partner, pageable, count);

			PageResult<CPOHUBPartnerModel> pagedResult = new PageResult<>(partners);

			if (pagedResult.getTotalElements() == 0) {

				if (values == null || values.stream().allMatch(Objects::isNull)) {
				} else {
					throw new DataNotFoundException("No data found for the given search criteria");
				}
			}

			return pagedResult;
		}
		return null;
	}

	@Override
	public PageResult<CPOHUBDowntimeModel> getDowntimeByPartnerId(String partnerId, int pagesize, int page,
			Map<String, List<String>> filters) {

		User currentUser = userService.getCurrentUser();

		if (!currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {
			System.out.println("If user is not admin");
			return new PageResult<>(Page.empty());
		}

		Query query = new Query();
		query.addCriteria(Criteria.where("partnerId").is(partnerId));
		query.with(Sort.by(Sort.Order.desc("endTime")));
		System.out.println("query   " + query);
//		if (filters != null && !filters.isEmpty()) {
//			for (Map.Entry<String, List<String>> entry : filters.entrySet()) {
//				query.addCriteria(Criteria.where(entry.getKey()).in(entry.getValue()));
//			}
//		}

		Pageable pageable = PageRequest.of(page, pagesize);
		System.out.println("pageable  " + pageable);
		List<CPOHUBDowntimeModel> partner = mongoTemplate.find(query.with(pageable), CPOHUBDowntimeModel.class);

		LOGGER.info("partner.getDowntimeByPartnerId :-   [" + partner.size() + "]");
//		partner.forEach(model -> {
//			if (model.getStartTime() != null && model.getEndTime() != null) {
//				try {
//					System.out.println("inside try block");
//
//					long diff = Duration.between(model.getStartTime(), model.getEndTime()).toMinutes();
//					model.setduration(diff);
//
//				} catch (Exception e) {
//					LOGGER.error("Error calculating duration for model ID [{}]", model.getId(), e.getMessage());
//				}
//			}
//		});

		long count = mongoTemplate.count(query, CPOHUBDowntimeModel.class);

		Page<CPOHUBDowntimeModel> partners = new PageImpl<>(partner, pageable, count);

//		System.out.println(" Page<CPOHUBDowntimeModel> partners   " + partners);
//		LOGGER.info("count   [" + count + "]");
//		LOGGER.info("partner   [" + partner + "]");
		return new PageResult<>(partners);

//		Query query = new Query();
//
//		query.addCriteria(Criteria.where("partnerId").is(partnerId));
//		query.with(Sort.by(Sort.Order.desc("endTime")));
//
//		Pageable pageable = PageRequest.of(page, pagesize);
//
//		List<CPOHUBDowntimeModel> partner = mongoTemplate.find(query.with(pageable), CPOHUBDowntimeModel.class);
//
//		partner.forEach(model -> {
//			if (model.getStartTime() != null && model.getEndTime() != null) {
//				long diff = Duration.between(model.getStartTime(), model.getEndTime()).toSeconds();
//				model.setduration(diff);
//			}
//		});
//
//		long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOHUBDowntimeModel.class);
//
//		Page<CPOHUBDowntimeModel> partners = new PageImpl<>(partner, pageable, count);
//
//		return new PageResult<>(partners);

	}

	@Override
	public CPOHUBPartnerModel getPartnerById(String id) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User currentUser = userService.getCurrentUser();

		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())
				|| currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			query.addCriteria(Criteria.where("party_id").is(currentUser.getParty_id()));
		}
		CPOHUBPartnerModel partner = mongoTemplate.findOne(query, CPOHUBPartnerModel.class);
		return partner;

	}

	@Override
	public CPOHUBPartnerModel getPartnerByUserId(String id) {

		Query query = new Query();

//		query.addCriteria(Criteria.where("id").is(userId));
		User currentUser = userService.getUserById(id);
//		User currentUser = userService.getCurrentUser();

		System.err.println("currentUser  " + currentUser.toString());
		if (!currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {
//			query.addCriteria(Criteria.where("party_id").is(currentUser.getParty_id()));
			query.addCriteria(Criteria.where("party_id").is(currentUser.getParty_id()).and("country_code")
					.is(currentUser.getCountry_code()));

			CPOHUBPartnerModel partner = mongoTemplate.findOne(query, CPOHUBPartnerModel.class);
			return partner;
		}
		return null;
	}

//	@Override
//	public CPOHUBDowntimeModel getDowntimeById(String id) {
//
//		// TODO Auto-generated method stub
//		return downTimeRepository.findById(id).get();
//	}

	@Override
	public List<CPOEndpointModel> getEndPointsByPartnerId(String partnerId) {

		CPOHUBPartnerModel partnerById = getPartnerById(partnerId);

		List<CPOEndpointModel> findByParty_idAndCountry_code = cpoEndpointRepository
				.findByPartyIdAndCountryCode(partnerById.getParty_id(), partnerById.getCountry_code());

		return findByParty_idAndCountry_code;
	}

	@Override
	public String initiate(CPODetails cpoDetails)
			throws JsonMappingException, JsonProcessingException, UserNotFoundException {

		if (cpoDetails == null) {
			throw new IllegalArgumentException("CPODetails cannot be null");
		}
		if (cpoDetails.getRole().equalsIgnoreCase("CPO")) {
			restTemplateService.sendDataViaPost(cpohub_url + "/ocpi/cpo/common/cpodetails", cpoDetails, "",
					cpoDetails.getPreFix());
		} else if (cpoDetails.getRole().equalsIgnoreCase("EMSP")) {
			restTemplateService.sendDataViaPost(cpohub_url + "/ocpi/cpo/common/cpodetails", cpoDetails, "",
					cpoDetails.getPreFix());
		}

		return "";

	}

	@Override
	public Object initiateByParterId(String partnerId)
			throws JsonMappingException, JsonProcessingException, UserNotFoundException {

		return "";
	}

	@Override
	public void weeklyBasedInitiate(String partyId, String identifier) {
		restTemplateService.sendDataViaPost(
				cpohub_url + "/ocpi/cpo/common/weeklyInitiate/" + partyId + "/" + identifier, "", partyId, "");

	}

//	@Override
//	public String getCpoPullFrequency(String partyId, String identifier, String reqId, String range) {
//
//		// Calculate end_date_time based on range
//		LocalDateTime endDateTime = LocalDateTime.now();
//		if (range == "1") {
//			LocalDateTime Interval_start_DateTime = endDateTime.minusHours(24); // Subtract 24 hours
//
//		} else if (range == "7") {
//			LocalDateTime Interval_End_DateTime = endDateTime.minusDays(7); // Subtract 7 days
//
//		}
//
//		// Format end_date_time as a string if required
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//		String formattedEndDateTime = endDateTime.format(formatter);
//
//		// Pass the calculated end_date_time to the API
//		restTemplateService.sendDataViaPost(cpohub_url + "/ocpi/cpo/common/weeklyInitiate/" + partyId + "/" + identifier
//				+ "/" + reqId + "/" + range, formattedEndDateTime, partyId, "");
//
//		return null;
//	}

	@Override
	public PageResult<CPOPullRequestModel> getAllPullRequest(String partnerId, int pagesize, int page) {

		Query query = new Query();
		Page<CPOPullRequestModel> pullRequests = null;

		if (partnerId != null) {
			query.addCriteria(Criteria.where("partnerId").is(partnerId));
			query.with(Sort.by(Sort.Order.desc("initiateTime")));

			Pageable pageable = PageRequest.of(page, pagesize);

			List<CPOPullRequestModel> lisPullRequest = mongoTemplate.find(query.with(pageable),
					CPOPullRequestModel.class);

			long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOPullRequestModel.class);

			pullRequests = new PageImpl<>(lisPullRequest, pageable, count);

		}
		return new PageResult<>(pullRequests);

	}

	@Override
	public PageResult<CPOPullRequestModel> cpoinitiate(Map<String, Object> map)
			throws NumberFormatException, ParseException {

		CPOEndpointModel cpoEndpointModel = cpoEndpointRepository.findEndPoints(map.get("identifier").toString(),
				map.get("partyId").toString());

		Optional<CPOHUBPartnerModel> partnerDetails = ocpihubPartnerRepository
				.findPartnerByPartyIdAndCountryCode(cpoEndpointModel.getParty_id(), cpoEndpointModel.getCountry_code());

		User currentUser = userService.getCurrentUser();

		String url = null;

		if (cpoEndpointModel != null) {

			if (map.get("range").toString().equalsIgnoreCase("Full")) {

				url = cpoEndpointModel.getUrl();

			} else {

				url = cpoEndpointModel.getUrl() + "?date_from="
						+ Utils.getCurrentUtcTime(Long.parseLong(map.get("range").toString()));

			}
			CPOPullRequestModel pullRequest = new CPOPullRequestModel();
			String reqId = UUID.randomUUID().toString();
			pullRequest.setUuid(reqId);
			pullRequest.setCountryCode(cpoEndpointModel.getCountry_code());
			pullRequest.setPartyId(cpoEndpointModel.getParty_id());
			pullRequest.setPartnerId(partnerDetails.get().getId());
			pullRequest.setRequestType(map.get("identifier").toString().toUpperCase());
			pullRequest.setInitiateTime(Instant.now());
			pullRequest.setStatus("ONGOING");
			pullRequest.setRequestedBy("Manually");
			pullRequest.setCreatedBy(currentUser.getUsername());
			mongoTemplate.save(pullRequest);

			Map<String, Object> requestmap = new HashMap<String, Object>();
			requestmap.put("id", reqId);
			requestmap.put("url", url);
			requestmap.put("partyId", cpoEndpointModel.getParty_id());
			requestmap.put("identifier", map.get("identifier").toString());
			requestmap.put("version", cpoEndpointModel.getVersion());

			ExecutorService executorService = Executors.newSingleThreadExecutor();

			executorService.submit((Runnable) () -> {
				try {
					restTemplateService.sendDataViaPost(cpohub_url + "/ocpi/cpo/common/cpoInitiate", requestmap, "",
							"");

				} catch (Exception e) {
					System.err.println("printStackTrace");
					// Log the error
//					System.err.println("Error processing session: " + e.getMessage());
					e.printStackTrace();
					e.getMessage();
				}
			});

			executorService.shutdown();

		}
		return getAllPullRequest(partnerDetails.get().getId(), 10, 0);

	}

	@Override
	public CPOSchedularFrequencyResponse addCPOSchedularFrequency(
			CPOSchedulerFrequencyModel cpoSchedulerFrequencyModel) {

		Optional<CPOSchedulerFrequencyModel> getFrequencyModel = cpoSchedulerFrequencyRepository
				.findByIdentifierAndPartyIdAndFrequency(cpoSchedulerFrequencyModel.getIdentifier(),
						cpoSchedulerFrequencyModel.getPartyId(), cpoSchedulerFrequencyModel.getFrequency());

		System.err.println("getFrequencyModel     " +getFrequencyModel.toString());
		if (!getFrequencyModel.isPresent()) {
			System.err.println("!getFrequencyModel.isPresent()     " +getFrequencyModel.toString());
			CPOHUBPartnerModel cpohubPartnerModel = ocpihubPartnerRepository
					.findPartnerByPartyId(cpoSchedulerFrequencyModel.getPartyId());

			CPOEndpointModel findEndpoint = cpoEndpointRepository
					.findEndPoints(cpoSchedulerFrequencyModel.getIdentifier(), cpoSchedulerFrequencyModel.getPartyId());

			CPOSchedulerFrequencyModel frequencyModel = new ModelMapper().map(cpoSchedulerFrequencyModel,
					CPOSchedulerFrequencyModel.class);
			frequencyModel.setPartnerId(cpohubPartnerModel.getId());
			if (frequencyModel != null)
				frequencyModel.setId(frequencyModel.getId());
			frequencyModel.setEndpoint(findEndpoint.getUrl());

			CPOSchedulerFrequencyModel frequencyModels = cpoSchedulerFrequencyRepository.save(frequencyModel);

			CPOSchedularFrequencyResponse convertToResponse = convertToResponse(frequencyModels);
			return convertToResponse;

		}

		return null;

	}

	@Override
	public CPOSchedularFrequencyResponse updateCPOSchedulerFrequency(
			CPOSchedulerFrequencyModel cpoSchedulerFrequencyModel, String id) {

		Optional<CPOSchedulerFrequencyModel> findById = cpoSchedulerFrequencyRepository.findById(id);

		Optional<CPOSchedulerFrequencyModel> getFrequencyModel = cpoSchedulerFrequencyRepository
				.findByIdentifierAndPartyIdAndFrequency(cpoSchedulerFrequencyModel.getIdentifier(),
						cpoSchedulerFrequencyModel.getPartyId(), cpoSchedulerFrequencyModel.getFrequency());

		if (findById.isPresent()) {

			if (!getFrequencyModel.isPresent()) {

				CPOHUBPartnerModel cpohubPartnerModel = ocpihubPartnerRepository
						.findPartnerByPartyId(cpoSchedulerFrequencyModel.getPartyId());

				CPOEndpointModel findEndpoint = cpoEndpointRepository.findEndPoints(
						cpoSchedulerFrequencyModel.getIdentifier(), cpoSchedulerFrequencyModel.getPartyId());

				CPOSchedulerFrequencyModel model = findById.get();
				model.setCountryCode(cpoSchedulerFrequencyModel.getCountryCode());
				model.setEndpoint(findEndpoint.getUrl());
				model.setPartnerId(cpohubPartnerModel.getId());
				model.setFrequency(cpoSchedulerFrequencyModel.getFrequency());
				model.setIdentifier(cpoSchedulerFrequencyModel.getIdentifier());
				model.setRole(cpoSchedulerFrequencyModel.getRole());
				model.setPartyId(cpoSchedulerFrequencyModel.getPartyId());

				CPOSchedulerFrequencyModel frequencyModels = cpoSchedulerFrequencyRepository.save(model);

				CPOSchedularFrequencyResponse convertToResponse = convertToResponse(frequencyModels);
				return convertToResponse;

			}
		}
		return null;

	}

	@Override
	public CPOSchedularFrequencyResponse getCPOSchedulerFrequencyById(String id) {
		// TODO Auto-generated method stub

		Optional<CPOSchedulerFrequencyModel> findById = cpoSchedulerFrequencyRepository.findById(id);

		CPOSchedularFrequencyResponse frequencyModel = new CPOSchedularFrequencyResponse();
		frequencyModel.setCountryCode(findById.get().getCountryCode());
		frequencyModel.setPartyId(findById.get().getPartyId());
		frequencyModel.setRole(findById.get().getRole());
		frequencyModel.setId(findById.get().getId());
		frequencyModel.setFrequency(findById.get().getFrequency());
		frequencyModel.setIdentifier(findById.get().getIdentifier());
		return frequencyModel;
	}

	@Override
	public CPOSchedularFrequencyResponse deleteCPOSchedulerFrequency(String id) {
		// TODO Auto-generated method stub

		Optional<CPOSchedulerFrequencyModel> findById = cpoSchedulerFrequencyRepository.findById(id);
		if (findById.isPresent()) {

			cpoSchedulerFrequencyRepository.deleteById(id);
			CPOSchedularFrequencyResponse frequencyModel = new CPOSchedularFrequencyResponse();

			frequencyModel.setId(id);
			return frequencyModel;

		}
		return null;
	}

	@Override
	public PageResult<CPOSchedularFrequencyResponse> getSchedularFrequencyTable(String partnerId, int pagesize,
			int page, Map<String, String> filter) {

		// Create the query and update objects
		Query query = new Query();
		query.addCriteria(Criteria.where("partnerId").is(partnerId));
		Pageable pageable = PageRequest.of(page, pagesize);

		List<CPOSchedulerFrequencyModel> lisPullRequest = mongoTemplate.find(query.with(pageable),
				CPOSchedulerFrequencyModel.class);

		long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOSchedulerFrequencyModel.class);

		// Convert the CPOSchedularFrequencyModel to CPOSchedularFrequencyResponse
		List<CPOSchedularFrequencyResponse> responseList = lisPullRequest.stream().map(this::convertToResponse)
				.collect(Collectors.toList());

		// Create and return the page result
		Page<CPOSchedularFrequencyResponse> pullRequests = new PageImpl<>(responseList, pageable, count);

		return new PageResult<>(pullRequests);
	}

	// Conversion method from CPOSchedularFrequencyModel to
	// CPOSchedularFrequencyResponse
	private CPOSchedularFrequencyResponse convertToResponse(CPOSchedulerFrequencyModel model) {
		CPOSchedularFrequencyResponse response = new CPOSchedularFrequencyResponse();

		response.setIdentifier(model.getIdentifier());
		response.setFrequency(model.getFrequency());
		response.setPartyId(model.getPartyId());
		response.setCountryCode(model.getCountryCode());
		response.setRole(model.getRole());
		response.setId(model.getId());
		return response;
	}

}