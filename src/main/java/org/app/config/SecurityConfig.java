package org.app.config;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SecurityConfig {
  public String hash(String password) {
    return BcryptUtil.bcryptHash(password);
  }

  public boolean matches(String password, String hash) {
    return BcryptUtil.matches(hash, password);
  }
}
