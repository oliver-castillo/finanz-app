package org.app.domain.service;

import java.util.Set;

public interface JwtProvider {
  String generateToken(String username, Set<String> roles);
}
