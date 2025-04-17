package com.evgateway.cpohubserver.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evgateway.cpohubserver.model.CPOHUBPartnerModel;
import com.evgateway.cpohubserver.repository.PartnerRepository;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private PartnerRepository partnerRepository;
	
	@Autowired
	UserService userService;


	@Override
	public List<Map<String, Object>> getPartyDetails(String role) {
		List<CPOHUBPartnerModel> findById = partnerRepository.findByRole(role);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (CPOHUBPartnerModel m : findById) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("party_id", m.getParty_id());
			map.put("country_code", m.getCountry_code());
			list.add(map);

		}
		return list;
	}

	
}
