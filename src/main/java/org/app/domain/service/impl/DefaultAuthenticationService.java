package org.app.domain.service.impl;

import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.app.domain.repository.RefreshTokenRepository;
import org.app.domain.service.AuthenticationService;
import org.app.domain.service.JwtProvider;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignInResponse;

@ApplicationScoped
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {
  private final JwtProvider jwtProvider;
  private final IdentityProviderManager identityProviderManager;
  private final RefreshTokenRepository refreshTokenRepository;

  @Override
  public SignInResponse signIn(SignInRequest signInRequest) {
    UsernamePasswordAuthenticationRequest authRequest =
        new UsernamePasswordAuthenticationRequest(
            signInRequest.getEmail(),
            new PasswordCredential(signInRequest.getPassword().toCharArray()));

    SecurityIdentity identity = identityProviderManager
        .authenticate(authRequest)
        .await().indefinitely();

    refreshTokenRepository.persist(identity.getPrincipal().getName());

    String token = jwtProvider.generateToken(
        identity.getPrincipal().getName(),
        identity.getRoles());

    return new SignInResponse().accessToken(token);
  }
}
