package com.evgateway.cpohubserver.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.evgateway.cpohubserver.cnum.ERole;
import com.evgateway.cpohubserver.exception.DataNotFoundException;
import com.evgateway.cpohubserver.exception.UserNotFoundException;
import com.evgateway.cpohubserver.model.CPOHUBEMSPPermission;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.model.UserSession;
import com.evgateway.cpohubserver.repository.CPOHUBEMSPPermissionRepository;
import com.evgateway.cpohubserver.repository.UserRepository;
import com.evgateway.cpohubserver.request.PageResult;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private CPOHUBEMSPPermissionRepository cpohubemspPermissionRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public User registerUser(User user) {

		if (user.getEmail() == null)
			throw new IllegalArgumentException("Email is required");

		if (userRepository.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("Email is already in use");
		}

		user.setLastUpdated(Instant.now());
		user.setActive(true);
		user.setUsername(user.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(ERole.ADMIN.toString());

		return userRepository.save(user);
	}

	@Override
	public String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			return ((UserDetails) authentication.getPrincipal()).getUsername();
		}
		return null;
	}

	@Override
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {

			return getUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
		}
		return null;
	}

	@Override
	public String getCurrentUserRoles() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList()).get(0);
		}
		return null;
	}

	@Override
	public PageResult<User> getTableData(int pageSize, int page, Map<String, List<String>> filters) {

		Query query = new Query();

		// Exclude ADMIN role
		query.addCriteria(Criteria.where("role").ne(ERole.ADMIN.toString()));

		// Handle filters with "or" condition
		List<String> values = null;

		if (filters != null && !filters.isEmpty()) {
			List<String> keys = filters.get("key");
			values = filters.get("value");

			if (keys != null && values != null && !keys.isEmpty() && !values.isEmpty()) {
				List<Criteria> orCriteria = new ArrayList<>();

				for (String key : keys) {
					for (String value : values) {
						if (key != null && value != null) {
							// Check if the key is "id" and convert to ObjectId if valid
							if (key.equals("id")) {
								try {
									orCriteria.add(Criteria.where(key).is(new ObjectId(value.trim())));
								} catch (IllegalArgumentException e) {
									// Log and skip invalid ObjectId values

								}
							} else {
								// Default behavior for other keys (using regex for case-insensitive search)
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

		Pageable pageable = PageRequest.of(page, pageSize);
		query.with(Sort.by(Sort.Order.desc("lastUpdated")));

		// Filter by current user's party ID and country code if not ADMIN
		User currentUser = getUserByUsername(getCurrentUsername());
		if (currentUser != null && !currentUser.getRole().equalsIgnoreCase(ERole.ADMIN.toString())) {
			query.addCriteria(Criteria.where("party_id").regex(".*" + currentUser.getParty_id() + ".*", "i"));
			query.addCriteria(Criteria.where("country_code").regex(".*" + currentUser.getCountry_code() + ".*", "i"));
		}

		List<User> users = mongoTemplate.find(query.with(pageable), User.class);
		long count = mongoTemplate.count(query.skip(-1).limit(-1), User.class);

		Page<User> userPage = new PageImpl<>(users, pageable, count);
		PageResult<User> pagedResult = new PageResult<>(userPage);

		if (pagedResult.getTotalElements() == 0) {

			if (values == null || values.stream().allMatch(Objects::isNull)) {
			} else {
				throw new DataNotFoundException("No data found for the given search criteria");
			}
		}
		return pagedResult;

	}

	@Override
	public User addUser(User userDTO) {

		if (userDTO.getFirstName() == null || userDTO.getFirstName() == "")
			throw new IllegalArgumentException("First Name is required");

		if (userDTO.getFirstName().length() < 3) {
			throw new IllegalArgumentException("First Name must be at least 3 characters long");
		}

		if (userDTO.getLastName() == null || userDTO.getLastName() == "")
			throw new IllegalArgumentException("Last Name is required");
		if (userDTO.getLastName().length() < 3)
			throw new IllegalArgumentException("Last Name must be at least 3 characters long");

		if (userDTO.getEmail() == null || userDTO.getEmail() == "") {
			throw new IllegalArgumentException("Email is required and cannot be empty.");
		}
		if (userDTO.getEmail().contains(" ")) {
			throw new IllegalArgumentException("Email must not contain spaces");
		}
		if (userDTO.getEmail().chars().filter(ch -> ch == '@').count() != 1) {
			throw new IllegalArgumentException("Email must contain exactly one '@' symbol");
		}
		if (!userDTO.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			throw new IllegalArgumentException("Email format is invalid.");
		}
		if (userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()) {
			throw new IllegalArgumentException("Password is required and cannot be empty.");
		}

		if (!userDTO.getPassword().matches("^(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
			throw new IllegalArgumentException(
					"Password must be at least 8 characters long and include at least one special character (e.g., @$!%*?&)");
		}

		
		if (userDTO.getRole() == null || userDTO.getRole() == "")
			throw new IllegalArgumentException("Role is invalid or missing");

		if (!isValidRole(userDTO.getRole())) {
			throw new IllegalArgumentException("Invalid role. Must be CPOADMIN, EMSPADMIN, or ADMIN.");
		}

		// if (userDTO.getCountry_code() == null)
		// 	throw new IllegalArgumentException("CountryCode is required");

		// if (userDTO.getParty_id() == null)
		// 	throw new IllegalArgumentException("Partyid is required");

		if (userRepository.existsByEmail(userDTO.getEmail())) {
			throw new IllegalArgumentException("Email is already in use");
		}

		userDTO.setUsername(userDTO.getEmail());
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hash the password
		userDTO.setLastUpdated(Instant.now()); // Set the current time when user is created
		userDTO.setActive(true); // Set the current time when user is created
		return userRepository.save(userDTO);
	}

	private boolean isValidRole(String role) {
		return role != null && (role.equals("CPOADMIN") || role.equals("EMSPADMIN") || role.equals("ADMIN"));
	}

	@Override
	public User getUserById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User currentUser = getCurrentUser();

		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())
				|| currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			query.addCriteria(Criteria.where("party_id").is(currentUser.getParty_id()));
		}
		User user = mongoTemplate.findOne(query, User.class);
		if (user == null) {
			throw new UserNotFoundException(id);
		} else {
			return user;
		}

	}

	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
	}

	@Override
	public User updateUserById(String id, User updatedUser) {

		// Find the user by ID, or throw an exception if not found
		User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		if (!updatedUser.getPassword().isEmpty() || updatedUser.getPassword() == null) {
		
			existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
		}
		// existingUser.setRole(updatedUser.getRole());
		existingUser.setActive(updatedUser.getActive());

		// Set the last updated timestamp
		existingUser.setLastUpdated(Instant.now());

		// Save and return the updated user
		return userRepository.save(existingUser);
	}

	@Override
	public User updateCurrentUserById(String id, User updatedUser) {

		// Find the user by ID, or throw an exception if not found
		User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		if (updatedUser.getFirstName() == null || updatedUser.getFirstName() == "")
			throw new IllegalArgumentException("First Name is required");

		if (updatedUser.getFirstName().length() < 3) {
			throw new IllegalArgumentException("First Name must be at least 3 characters long");
		}
		if (!updatedUser.getFirstName().matches("^[A-Za-z]+$")) {
			throw new IllegalArgumentException("First Name must contain only alphabets");
		}

		if (updatedUser.getLastName() == null || updatedUser.getLastName() == "")
			throw new IllegalArgumentException("Last Name is required");
		if (updatedUser.getLastName().length() < 3)
			throw new IllegalArgumentException("Last Name must be at least 3 characters long");

		if (!updatedUser.getLastName().matches("^[A-Za-z]+$")) {
			throw new IllegalArgumentException("Last Name must contain only alphabets");
		}
		// Update fields

		existingUser.setFirstName(updatedUser.getFirstName());
		existingUser.setLastName(updatedUser.getLastName());

		// Set the last updated timestamp
		existingUser.setLastUpdated(Instant.now());

		// Save and return the updated user
		return userRepository.save(existingUser);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
	}

	@Override
	public UserSession getUserSessionById(String id) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		User currentUser = getCurrentUser();

		if (currentUser.getRole().equalsIgnoreCase(ERole.CPOADMIN.toString())
				|| currentUser.getRole().equalsIgnoreCase(ERole.EMSPADMIN.toString())) {
			query.addCriteria(Criteria.where("userId").is(currentUser.getId()));
		}

		UserSession userSession = mongoTemplate.findOne(query, UserSession.class);
		return userSession;

	}

	@Override
	public PageResult<UserSession> getUserSessionByUserId(int pagesize, int page, Map<String, String> filters,
			String userId) {

		// Match stage: Filter by userId and any additional filters
		Criteria matchCriteria = Criteria.where("userId").is(userId);
		if (filters != null && !filters.isEmpty()) {
			filters.forEach((key, value) -> {
				if (key != null && value != null) {
					matchCriteria.and(key).regex(".*" + value + ".*", "i");
				}
			});
		}

		// Sort stage: First by isActive (true first), then by logoutTime (descending)
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(matchCriteria),
				Aggregation.sort(Sort.by(Sort.Order.desc("isActive"), Sort.Order.desc("logoutTime"))),
				Aggregation.skip((long) page * pagesize), Aggregation.limit(pagesize));
		// Fetch the data
		List<UserSession> userSessions = mongoTemplate
				.aggregate(aggregation, "ocpi_cpo_user_sessions", UserSession.class).getMappedResults();

		long count = mongoTemplate.count(Query.query(matchCriteria), UserSession.class);

		Pageable pageable = PageRequest.of(page, pagesize);
		Page<UserSession> userPage = new PageImpl<>(userSessions, pageable, count);

		return new PageResult<>(userPage);
	}

	@Override
	public PageResult<User> getUserByPartyIdAndCountryCode(int pagesize, int page, Map<String, String> filters,
			String party_id, String country_code) {

		Query query = new Query();
		// Add criteria for filtering by party_id and country_code
		query.addCriteria(Criteria.where("party_id").is(party_id).and("country_code").is(country_code));

		// Add additional filters if present
		if (filters != null && !filters.isEmpty()) {
			Criteria filterCriteria = new Criteria();
			filters.forEach((key, value) -> {
				if (key != null && value != null) {
					filterCriteria.and(key).regex(".*" + value + ".*", "i"); // Case-insensitive matching
				}
			});
			query.addCriteria(filterCriteria);
		}
		Pageable pageable = PageRequest.of(page, pagesize);
		query.with(Sort.by(Sort.Order.desc("lastUpdated")));
		List<User> users = mongoTemplate.find(query.with(pageable), User.class);

		long count = mongoTemplate.count(Query.of(query).skip(-1).limit(-1), User.class);

		Page<User> userPage = new PageImpl<>(users, pageable, count);

		return new PageResult<>(userPage);
	}

	@Override
	public Object getAllPermissionBasedOnPartyId(String role, String partyId) throws Exception {

		Optional<CPOHUBEMSPPermission> cpoPermissionDetails = cpohubemspPermissionRepository.findByEmspPartyId(partyId);

		try {
			if (cpoPermissionDetails.isPresent()) {
				return cpoPermissionDetails;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}

		return null;

	}

	@Override
	public Object updateAndSaveEMSPPermissionBasedOnPartyId(String emspPartyId, CPOHUBEMSPPermission emspPermission) {
		CPOHUBEMSPPermission emspDetails = cpohubemspPermissionRepository.findByEmspPartyId(emspPartyId).get();

		try {
			if (emspDetails != null) {

				emspDetails.setCpo_partyIds(emspPermission.getCpo_partyIds());
				emspDetails.setLocation_country_names(emspPermission.getLocation_country_names());

				return cpohubemspPermissionRepository.save(emspDetails);

			}
		} catch (Exception e) {
			e.getMessage();
			// e.printStackTrace();
		}

		return null;

	}

}