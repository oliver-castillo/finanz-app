package org.app.domain.service;

import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignInResponse;

public interface AuthenticationService {
  SignInResponse signIn(SignInRequest signInRequest);
}
