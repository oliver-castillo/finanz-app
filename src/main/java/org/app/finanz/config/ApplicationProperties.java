package org.app.finanz.config;

import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Getter
public class ApplicationProperties {
  @ConfigProperty(name = "app.refresh_token_expiration")
  public Integer refreshTokenExpiration;
}
