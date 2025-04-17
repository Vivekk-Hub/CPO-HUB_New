package com.evgateway.cpohubserver.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.evgateway.cpohubserver.cnum.ERole;
import com.evgateway.cpohubserver.exception.UserNotFoundException;
import com.evgateway.cpohubserver.model.CPOHUBRequest;
import com.evgateway.cpohubserver.model.CPOSessionsActivityModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.repository.UserRepository;
import com.evgateway.cpohubserver.request.PageResult;

@Service
public class EmspRequestServiceImpl implements EmspRequestService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public PageResult<CPOHUBRequest> emspRequest(int pagesize, int page, Map<String, List<String>> filters) {

		User currentUser = getUserByUsername(userService.getCurrentUsername());
		Query query = new Query();

		if (currentUser != null && !currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {

			if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
				query.addCriteria(Criteria.where("cpo_party_id").is(currentUser.getParty_id())
						.and("cpo_country_code").is(currentUser.getCountry_code()));

			}

			Pageable pageable = PageRequest.of(page, pagesize);

			// Apply filters if provided
			if (filters != null && !filters.isEmpty()) {
				List<String> keys = filters.get("key");
				List<String> values = filters.get("value");

				if (keys != null && values != null && !keys.isEmpty() && !values.isEmpty()) {
					List<Criteria> orCriteria = new ArrayList<>();

					for (String key : keys) {
						for (String value : values) {
							if (key != null && value != null) {
								if (key.equals("id")) {
									try {
										orCriteria.add(Criteria.where(key).is(new ObjectId(value.trim())));
									} catch (IllegalArgumentException e) {
										// System.err.println("Invalid ObjectId format for id: " + value);
										e.getMessage();
									}
								} else if (key.equals("last_updated")) {
									try {
										Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
												.parse(value.trim());
										orCriteria.add(Criteria.where(key).gte(date));
									} catch (ParseException e) {
										// System.err.println("Invalid date format for last_updated: " + value);
										e.getMessage();
									}
								} else {
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

			// Apply sorting before pagination
			query.with(Sort.by(Sort.Order.desc("last_updated"), Sort.Order.desc("status")));

			// Execute the query with pagination
			List<CPOHUBRequest> emspRequest = mongoTemplate.find(query.with(pageable), CPOHUBRequest.class);
			long count = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), CPOHUBRequest.class);

			Page<CPOHUBRequest> emspRequestPage = new PageImpl<>(emspRequest, pageable, count);
			PageResult<CPOHUBRequest> pagedResult = new PageResult<>(emspRequestPage);

			if (pagedResult.getTotalElements() == 0)
				return new PageResult<>(new PageImpl<>(Collections.emptyList(), pageable, 0));

			return pagedResult;
		}

		return null;
	}

	public User getUserByUsername(String username) {

		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
	}

	@Override
	public CPOHUBRequest getEmspRequestById(String id) {

		User currentUser = userService.getCurrentUser();

		if (currentUser != null) {

			if (!currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {

				CPOHUBRequest emspRequest = mongoTemplate.findById(id, CPOHUBRequest.class);

				return emspRequest;
			}

		}
		return null;

	}

	@Override
	public CPOSessionsActivityModel getSessionActivityById(String id) {
		User currentUser = userService.getCurrentUser();

		if (currentUser != null) {

			if (currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {

				Query query = new Query();

				query.addCriteria(Criteria.where("session_id").is(id));

				CPOSessionsActivityModel sessionAcitivity = mongoTemplate.findOne(query,
						CPOSessionsActivityModel.class);
				return sessionAcitivity;
			}

		}
		return null;
	}

}
