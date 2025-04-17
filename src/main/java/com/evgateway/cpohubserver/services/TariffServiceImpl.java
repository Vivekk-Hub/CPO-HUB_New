package com.evgateway.cpohubserver.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.evgateway.cpohubserver.model.CPOTariffModel;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.request.PageResult;

@Service
public class TariffServiceImpl implements TariffService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserService userService;

	@Override
	public PageResult<CPOTariffModel> getTariffTableData(int pagesize, int page, Map<String, List<String>> filters) {

		Query query = new Query();
		User currentUser = userService.getCurrentUser();
		if (!currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {

			query.addCriteria(Criteria.where("cpo_party_id").is(currentUser.getParty_id()).and("cpo_country_code")
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
							if (key.equals("uid")) {
								// Handle 'uid' as an ObjectId
								try {
									orCriteria.add(Criteria.where(key).is(new ObjectId(value.trim())));
								} catch (IllegalArgumentException e) {
									// Handle invalid ObjectId gracefully
								}
							} else if (key.equals("last_updated")) {
								// Handle date search for 'last_updated'
								try {
									Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
											.parse(value.trim());
									orCriteria.add(Criteria.where(key).gte(date));
								} catch (ParseException e) {
									// Handle invalid date format gracefully
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

		
		// Apply sorting and pagination AFTER adding filters
		Pageable pageable = PageRequest.of(page, pagesize);
		query.with(Sort.by(Sort.Order.desc("last_updated")));
		query.with(pageable);

		List<CPOTariffModel> tariff = mongoTemplate.find(query.with(pageable), CPOTariffModel.class);
//		long count = mongoTemplate.count(query.skip(-1).limit(-1), CPOTariffModel.class);
		long count = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), CPOTariffModel.class);

		Page<CPOTariffModel> tariffs = new PageImpl<>(tariff, pageable, count);
		PageResult<CPOTariffModel> pagedResult = new PageResult<>(tariffs);

		// if (pagedResult.getTotalElements() == 0) {

		// 	if (values == null || values.stream().allMatch(Objects::isNull)) {
		// 	} else {
		// 		throw new DataNotFoundException("No data found for the given search criteria");
		// 	}
		// }

		return pagedResult;

	}

	@Override
	public CPOTariffModel getTariffById(String id) {
		// TODO Auto-generated method stub
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

		List<CPOTariffModel> find = mongoTemplate.find(query, CPOTariffModel.class);

		if (find.size() > 0) {
			return find.get(0);
		}
		return null;

	}

}
