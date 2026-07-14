package org.app.domain.service;

import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignInResponse;
import org.openapitools.model.SignUpRequest;
import org.openapitools.model.SignUpResponse;

public interface AuthenticationService {
  SignUpResponse signUp(SignUpRequest signUpRequest);

  SignInResponse signIn(SignInRequest signInRequest);
}
