package com.evgateway.cpohubserver.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.evgateway.cpohubserver.model.UserSession;

public interface UserSessionService {

    public UserSession createSession(String userId, HttpServletRequest request,String token);

    public void endSession(HttpServletRequest request);

    public List<UserSession> getSessionHistory(String userId);

    public List<UserSession> getActiveSessions();

}
