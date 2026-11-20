package org.app.finanz.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.app.finanz.service.AuthenticationService;
import org.openapitools.api.AuthApi;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignUpRequest;

@ApplicationScoped
@RequiredArgsConstructor
public class AuthenticationController implements AuthApi {
  private final AuthenticationService authenticationService;

  @Override
  public Response signInUser(SignInRequest signInRequest) {
    return Response.ok(authenticationService.signIn(signInRequest)).build();
  }

  @Override
  public Response signUpUser(SignUpRequest signUpRequest) {
    return Response.ok(authenticationService.signUp(signUpRequest)).build();
  }
}
