package com.evgateway.cpohubserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.evgateway.cpohubserver.model.UserSession;

@Repository
public interface UserSessionRepository extends MongoRepository<UserSession, String> {
    
	
    List<UserSession> findByUserId(String userId);
    
    List<UserSession> findByIsActive(boolean isActive);

    Optional<UserSession> findBySessionId(String sessionId);
    
}
