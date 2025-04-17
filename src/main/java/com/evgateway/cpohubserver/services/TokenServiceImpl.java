package com.evgateway.cpohubserver.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
import com.evgateway.cpohubserver.exception.DataNotFoundException;
import com.evgateway.cpohubserver.model.CPOTokenModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.request.PageResult;

@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserService userService;

	@Override
	public PageResult<CPOTokenModel> getTokenTableData(int pagesize, int page, Map<String, List<String>> filters) {

		User currentUser = userService.getCurrentUser();
		Query query = new Query();

		if (currentUser != null && !currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {


			if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
				query.addCriteria(Criteria.where("party_id").is(currentUser.getParty_id()).and("country_code")
						.is(currentUser.getCountry_code()));
			}

			Pageable pageable = PageRequest.of(page, pagesize);

			// Apply filters if provided
			List<String> values = null;

			if (filters != null && !filters.isEmpty()) {
				List<String> keys = filters.get("key");
				values = filters.get("value");

				if (keys != null && values != null && !keys.isEmpty() && !values.isEmpty()) {
					List<Criteria> orCriteria = new ArrayList<>();

					for (String key : keys) {
						for (String value : values) {
							if (key != null && value != null) {
								if (key.equals("id")) {
									
									if (ObjectId.isValid(value.trim())) {
										// Full ObjectId match
										orCriteria.add(Criteria.where("_id").is(new ObjectId(value.trim())));
									} else {
										// Partial match using regex (search as string)
										orCriteria.add(Criteria.where("_id").regex("^" + value.trim(), "i"));

									}

								} else if (key.equals("last_updated")) {
									try {
										Date date;
										try {
											date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
													.parse(value.trim());
										} catch (ParseException e) {
											date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(value.trim());
										}
										orCriteria.add(Criteria.where(key).gte(date));
									} catch (ParseException e) {
										// Handle parsing error (e.g., log it)
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
			query.with(Sort.by(Sort.Order.desc("last_updated")));
			// Apply sorting before pagination
			// query.with(Sort.by(Sort.Order.desc("status"),
			// Sort.Order.desc("last_updated")));

			// Execute the query with pagination
			List<CPOTokenModel> emspRequest = mongoTemplate.find(query.with(pageable), CPOTokenModel.class);
			long count = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), CPOTokenModel.class);

			Page<CPOTokenModel> emspRequestPage = new PageImpl<>(emspRequest, pageable, count);
			PageResult<CPOTokenModel> pagedResult = new PageResult<>(emspRequestPage);

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
	public CPOTokenModel getTokenById(String id) {
		// TODO Auto-generated method stub
		User currentUser = userService.getCurrentUser();
		Query query = new Query();
		query.addCriteria(Criteria.where("uid").is(id));
		if (currentUser != null && !currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())) {

			if (currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
				query.addCriteria(Criteria.where("party_id").is(currentUser.getParty_id()).and("country_code")
						.is(currentUser.getCountry_code()));
			}

			List<CPOTokenModel> find = mongoTemplate.find(query, CPOTokenModel.class);

			if (find.size() > 0) {
				return find.get(0);
			}
		}
		return null;

	}

}
