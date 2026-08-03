package org.app.finanz.service;

import org.app.finanz.domain.AuthResult;
import org.app.finanz.domain.User;

public interface AuthenticationService {
  AuthResult signUp(User user);

  AuthResult signIn(String email, String password);
}
