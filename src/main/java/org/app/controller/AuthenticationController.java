package org.app.controller;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.app.domain.model.User;
import org.app.domain.service.AuthenticationService;
import org.app.domain.service.UserService;
import org.app.mapper.UserMapper;
import org.openapitools.api.AuthApi;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignUpRequest;
import org.openapitools.model.SignUpResponse;

@RequiredArgsConstructor
public class AuthenticationController implements AuthApi {
  private final AuthenticationService authenticationService;
  private final UserMapper userMapper;
  private final UserService userService;

  @Override
  public Response signUpUser(SignUpRequest signUpRequest) {
    User mappedUser = userMapper.signUpRequestToUser(signUpRequest);
    User createdUser = userService.createUser(mappedUser);
    SignUpResponse userResponse = userMapper.userToSignUpResponse(createdUser);

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
