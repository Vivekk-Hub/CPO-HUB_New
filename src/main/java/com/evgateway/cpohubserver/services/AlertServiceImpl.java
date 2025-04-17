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
import com.evgateway.cpohubserver.model.CPOHUBAlertModel;
import com.evgateway.cpohubserver.model.CPOHUBApiLogModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.request.PageResult;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserService userService;

	@Override
	public PageResult<CPOHUBAlertModel> getAlertTableData(int pagesize, int page, Map<String, List<String>> filters) {
		System.err.println("Inside service");
		Query query = new Query();
		User currentUser = userService.getCurrentUser();
		if (currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {
			System.err.println("Inside ADMIN");
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
									// Handle 'uid' as an ObjectId
									try {
										orCriteria.add(Criteria.where(key).is(new ObjectId(value.trim())));
									} catch (IllegalArgumentException e) {
										// Handle invalid ObjectId gracefully
		                            System.err.println("Invalid ObjectId format for uid: " + value);
									}
								} else if (key.equals("timeStamp")) {
									// Handle date search for 'last_updated'
									try {
										Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
												.parse(value.trim());
										orCriteria.add(Criteria.where(key).gte(date));
									} catch (ParseException e) {
										// Handle invalid date format gracefully
//		                            System.err.println("Invalid date format for last_updated: " + value);
									}
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
			}System.err.println("query "+query);
			Pageable pageable = PageRequest.of(page, pagesize);
			query.with(Sort.by(Sort.Order.desc("timeStamp")));
			
			List<CPOHUBAlertModel> alerts = mongoTemplate.find(query.with(pageable), CPOHUBAlertModel.class);
			long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOHUBAlertModel.class);

			Page<CPOHUBAlertModel> alert = new PageImpl<>(alerts, pageable, count);

			PageResult<CPOHUBAlertModel> pagedResult = new PageResult<>(alert);
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
	public CPOHUBAlertModel getAlertById(String id) {
		User currentUser = userService.getCurrentUser();
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		if (currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {

			List<CPOHUBAlertModel> find = mongoTemplate.find(query, CPOHUBAlertModel.class);
			if (find.size() > 0) {
				return find.get(0);
			}
			return null;
		}
		return null;

	}

	@Override
	public PageResult<CPOHUBApiLogModel> getLogTableData(int pagesize, int page, Map<String, List<String>> filters) {

		Query query = new Query();
		User currentUser = userService.getCurrentUser();
		if (currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {

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
									// Handle 'uid' as an ObjectId
									try {
										orCriteria.add(Criteria.where(key).is(new ObjectId(value.trim())));
									} catch (IllegalArgumentException e) {
										// Handle invalid ObjectId gracefully
//		                            System.err.println("Invalid ObjectId format for uid: " + value);
									}
								} else if (key.equals("timestamp")) {
									// Handle date search for 'last_updated'
									try {
										Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
												.parse(value.trim());
										orCriteria.add(Criteria.where(key).gte(date));
									} catch (ParseException e) {
										// Handle invalid date format gracefully
//		                            System.err.println("Invalid date format for last_updated: " + value);
									}
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
			query.with(Sort.by(Sort.Order.desc("timestamp")));

			List<CPOHUBApiLogModel> logs = mongoTemplate.find(query.with(pageable), CPOHUBApiLogModel.class);
			long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOHUBApiLogModel.class);

			Page<CPOHUBApiLogModel> log = new PageImpl<>(logs, pageable, count);

			PageResult<CPOHUBApiLogModel> pagedResult = new PageResult<>(log);

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
	public CPOHUBApiLogModel getLogsById(String id) {
		User currentUser = userService.getCurrentUser();
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));

		if (currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {

			List<CPOHUBApiLogModel> find = mongoTemplate.find(query, CPOHUBApiLogModel.class);
			if (find.size() > 0) {
				return find.get(0);
			}
			return null;
		}
		return null;
	}

}
