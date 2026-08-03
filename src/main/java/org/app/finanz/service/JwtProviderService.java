package org.app.finanz.service;

import java.util.Set;

public interface JwtProviderService {
  String generateAccessToken(String username, Set<String> roles);
}
