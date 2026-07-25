package org.app.finanz.controller;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.app.finanz.domain.service.AuthenticationService;
import org.openapitools.api.AuthApi;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignUpRequest;
import org.openapitools.model.SignUpResponse;

@RequiredArgsConstructor
public class AuthenticationController implements AuthApi {
  private final AuthenticationService authenticationService;

  @Override
  public Response signUpUser(SignUpRequest signUpRequest) {
    SignUpResponse userResponse = authenticationService.signUp(signUpRequest);
    return Response.ok()
        .entity(userResponse)
        .status(Response.Status.CREATED)
        .build();
  }

  @Override
  public Response signInUser(SignInRequest signInRequest) {
    return Response.ok()
        .entity(authenticationService.signIn(signInRequest))
        .build();
  }
}
