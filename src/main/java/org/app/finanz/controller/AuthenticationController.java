package org.app.finanz.controller;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.AuthApi;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignUpRequest;

@RequiredArgsConstructor
public class AuthenticationController implements AuthApi {
  @Override
  public Response signInUser(SignInRequest signInRequest) {
    return null;
  }

  @Override
  public Response signUpUser(SignUpRequest signUpRequest) {
    return null;
  }
}
