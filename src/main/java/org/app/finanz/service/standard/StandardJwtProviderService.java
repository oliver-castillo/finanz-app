package org.app.finanz.service.standard;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.finanz.service.JwtProviderService;

import java.time.Duration;
import java.util.Set;

@ApplicationScoped
public class StandardJwtProviderService implements JwtProviderService {
  private final String issuer = System.getenv("JWT_ISSUER");

  @Override
  public String generateAccessToken(String username, Set<String> roles) {
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
