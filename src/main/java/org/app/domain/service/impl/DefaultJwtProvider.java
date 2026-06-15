package org.app.domain.service.impl;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.domain.service.JwtProvider;

import java.time.Duration;
import java.util.Set;

@ApplicationScoped
public class DefaultJwtProvider implements JwtProvider {
  private final String issuer = System.getenv("JWT_ISSUER");

  @Override
  public String generateToken(String username, Set<String> roles) {
    return Jwt
        .issuer(issuer)
        .subject(username)
        .groups(roles)
        .claim("email", username)
        .expiresIn(Duration.ofMinutes(15))
        .jws()
        .sign();
  }
}
