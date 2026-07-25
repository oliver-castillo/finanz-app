package org.app.infraestructure.adapter;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.domain.mapper.UserMapper;
import org.app.domain.service.AuthenticationService;
import org.app.domain.service.JwtProviderService;
import org.app.domain.util.ExceptionMessage;
import org.app.exception.AlreadyExistsException;
import org.app.infraestructure.persistence.entity.RefreshTokenEntity;
import org.app.infraestructure.persistence.entity.UserEntity;
import org.app.infraestructure.persistence.repository.PanacheRefreshTokenRepository;
import org.app.infraestructure.persistence.repository.PanacheUserRepository;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignInResponse;
import org.openapitools.model.SignUpRequest;
import org.openapitools.model.SignUpResponse;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * Default implementation of the AuthenticationService interface.
 */
@ApplicationScoped
@RequiredArgsConstructor
public class StandardAuthenticationService implements AuthenticationService {
  private final PanacheUserRepository panacheUserRepository;
  private final UserMapper userMapper;
  private final JwtProviderService jwtProviderService;
  private final IdentityProviderManager identityProviderManager;
  private final PanacheRefreshTokenRepository panacheRefreshTokenRepository;
  private final Instant expiresAt = Instant.now().plus(7, ChronoUnit.DAYS);

  /**
   * Sign up a new user.
   *
   * @param signUpRequest the sign-up request
   * @return SignUpResponse
   */
  @Override
  @Transactional
  public SignUpResponse signUp(SignUpRequest signUpRequest) {
    if (panacheUserRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new AlreadyExistsException(ExceptionMessage.EMAIL_ALREADY_EXISTS);
    }
    UserEntity userEntity = userMapper.toUserEntity(signUpRequest);
    userEntity.setPassword(BcryptUtil.bcryptHash(userEntity.getPassword()));
    panacheUserRepository.persist(userEntity);
    return userMapper.toSignUpResponse(userEntity);
  }

  /**
   * Sign in an existing user.
   *
   * @param signInRequest the sign in request
   * @return SignInResponse
   */
  @Override
  @Transactional
  public SignInResponse signIn(SignInRequest signInRequest) {
    UsernamePasswordAuthenticationRequest authRequest =
        new UsernamePasswordAuthenticationRequest(
            signInRequest.getEmail(),
            new PasswordCredential(signInRequest.getPassword().toCharArray()));

    SecurityIdentity identity = identityProviderManager
        .authenticate(authRequest)
        .await().indefinitely();

    RefreshTokenEntity refreshTokenEntity = buildRefreshTokenEntity(identity.getPrincipal().getName());
    panacheRefreshTokenRepository.persist(refreshTokenEntity);

    String token = jwtProviderService.generateToken(
        identity.getPrincipal().getName(),
        identity.getRoles());

    return new SignInResponse()
        .accessToken(token)
        .refreshToken(refreshTokenEntity.getToken());
  }

  /**
   * Build a RefreshTokenEntity for the given email.
   *
   * @param email the email of the user
   * @return RefreshTokenEntity
   */
  private RefreshTokenEntity buildRefreshTokenEntity(String email) {
    UserEntity userEntity = panacheUserRepository.findByEmail(email);

    RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
    refreshTokenEntity.setToken(UUID.randomUUID().toString());
    refreshTokenEntity.setUser(userEntity);
    refreshTokenEntity.setExpiresAt(expiresAt);
    return refreshTokenEntity;
  }
}
