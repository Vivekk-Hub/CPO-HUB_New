package com.evgateway.cpohubserver.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

  private String SECRET_KEY = "MfHjC8h+gdp9JYHhZkcbGjfHQk8RszFIzPZZxr1Fws8=";

  @SuppressWarnings("deprecation")
  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        // .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
        .setExpiration(new Date(System.currentTimeMillis() +  1000 * 60 * 5)) // 5 mins
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  @SuppressWarnings("deprecation")
  public String extractUsername(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean isTokenValid(String token) {
    return extractUsername(token) != null && !isTokenExpired(token);
  }

  @SuppressWarnings("deprecation")
  public boolean isTokenExpired(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());
  }
}
