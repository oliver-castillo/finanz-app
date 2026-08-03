package org.app.finanz.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Role role;
  private List<RefreshToken> refreshTokens = new ArrayList<>();
}
