package org.app.finanz.controller;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.app.finanz.controller.mapper.AuthMapper;
import org.app.finanz.controller.mapper.UserMapper;
import org.app.finanz.domain.AuthResult;
import org.app.finanz.domain.User;
import org.app.finanz.service.AuthenticationService;
import org.openapitools.api.AuthApi;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignUpRequest;

@RequiredArgsConstructor
public class AuthenticationController implements AuthApi {
  private final AuthenticationService authenticationService;
  private final AuthMapper authMapper;
  private final UserMapper userMapper;

  @Override
  public Response signUpUser(SignUpRequest signUpRequest) {
    User user = userMapper.toUser(signUpRequest);
    AuthResult authResult = authenticationService.signUp(user);
    return Response.ok()
        .entity(authMapper.toAuthResponse(authResult))
        .status(Response.Status.CREATED)
        .build();
  }

  @Override
  public Response signInUser(SignInRequest signInRequest) {
    AuthResult authResult = authenticationService.signIn(
        signInRequest.getEmail(),
        signInRequest.getPassword());
    return Response.ok()
        .entity(authMapper.toAuthResponse(authResult))
        .build();
  }
}
