package com.evgateway.cpohubserver.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
import com.evgateway.cpohubserver.exception.DataNotFoundException;
import com.evgateway.cpohubserver.model.CPOSessionsActivityModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.request.PageResult;

@Service
public class SessionServiceImpl implements SessionService {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserService userService;

	@Override
	public PageResult<CPOSessionsActivityModel> getUserSession(int pagesize, int page,
			Map<String, List<String>> filters) {
		Query query = new Query();
		User currentUser = userService.getCurrentUser();
		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {
			query.addCriteria(Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
					.is(currentUser.getCountry_code()));

		} else if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			query.addCriteria(Criteria.where("emsp_party_id").is(currentUser.getParty_id()).and("emsp_country_code")
					.is(currentUser.getCountry_code()));

		}
		query.addCriteria(Criteria.where("status").in("ACTIVE", "PENDING", "INVALID"));
		List<String> values = null;

		if (filters != null && !filters.isEmpty()) {
			List<String> keys = filters.get("key");
			values = filters.get("value");

			if (keys != null && values != null && !keys.isEmpty() && !values.isEmpty()) {
				List<Criteria> orCriteria = new ArrayList<>();

				for (String key : keys) {
					for (String value : values) {
						if (key != null && value != null) {
							if (key.equals("session_start_date_time")) {
								// Handle date search for 'last_updated'
								try {
									Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(value.trim());
									orCriteria.add(Criteria.where(key).gte(date));
								} catch (ParseException e) {
									// Handle invalid date format gracefully
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

		List<CPOSessionsActivityModel> users = mongoTemplate.find(query.with(pageable), CPOSessionsActivityModel.class);
		long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOSessionsActivityModel.class);

		Page<CPOSessionsActivityModel> userPage = new PageImpl<>(users, pageable, count);
		PageResult<CPOSessionsActivityModel> pagedResult = new PageResult<>(userPage);
		if (pagedResult.getTotalElements() == 0) {

			if (values == null || values.stream().allMatch(Objects::isNull)) {
			} else {
				throw new DataNotFoundException("No data found for the given search criteria");
			}
		}

		return pagedResult;

	}

	@Override
	public CPOSessionsActivityModel getSessionActivityById(String sessionId) {
		User currentUser = userService.getCurrentUser();

		if (currentUser != null) {

			Query query = new Query();

			query.addCriteria(Criteria.where("session_id").is(sessionId));

			CPOSessionsActivityModel sessionAcitivity = mongoTemplate.findOne(query, CPOSessionsActivityModel.class);
			return sessionAcitivity;
		}


		return null;
	}

}
