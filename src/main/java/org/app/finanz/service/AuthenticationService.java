package org.app.finanz.service;

import org.openapitools.model.AuthResponse;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignUpRequest;

public interface AuthenticationService {
  AuthResponse signUp(SignUpRequest request);

  AuthResponse signIn(SignInRequest request);
}
