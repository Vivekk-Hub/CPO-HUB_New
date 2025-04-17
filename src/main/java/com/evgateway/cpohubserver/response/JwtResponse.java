package com.evgateway.cpohubserver.response;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private String id;
	private String token;
	private String email;
	private String role;
	private String type;

	public JwtResponse(String id, String accessToken, String email,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.token = accessToken;
		this.email = email;
		this.role = authorities.iterator().next().getAuthority();
		this.type = "Bearer";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRoles(String role) {
		this.role = role;
	}

}
