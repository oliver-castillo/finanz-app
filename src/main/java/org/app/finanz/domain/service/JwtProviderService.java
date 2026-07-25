package org.app.finanz.domain.service;

import java.util.Set;

public interface JwtProviderService {
  String generateToken(String username, Set<String> roles);
}
