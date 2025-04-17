package com.evgateway.cpohubserver.services;

import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.model.CPOHUBEMSPPermission;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.model.UserSession;
import com.evgateway.cpohubserver.request.PageResult;

import jakarta.validation.Valid;

public interface UserService {

	public User registerUser(User user);

	public String getCurrentUsername();

	public String getCurrentUserRoles();

	public PageResult<User> getTableData(int pagesize, int page, Map<String, List<String>> filters);

	public User addUser(User userDTO);

	public User getUserById(String id);

	public User updateUserById(String id, User updatedUser);

	public User findByUsername(String username);

	public PageResult<User> getUserByPartyIdAndCountryCode(int pagesize, int page, Map<String, String> filters,
			String party_id, String country_code);

	PageResult<UserSession> getUserSessionByUserId(int pagesize, int page, Map<String, String> filters, String userId);

	public UserSession getUserSessionById(String id);

	public User getCurrentUser();

	public Object getAllPermissionBasedOnPartyId(String role, String emspPartyId) throws Exception;

	public Object updateAndSaveEMSPPermissionBasedOnPartyId(String emspPartyId, CPOHUBEMSPPermission emspPermission)
			throws Exception;

//	public User updateCurrentUserById(String id, @Valid Map<String, Object> updatedUser);

	public User updateCurrentUserById(String id, @Valid User updatedUser);

}
