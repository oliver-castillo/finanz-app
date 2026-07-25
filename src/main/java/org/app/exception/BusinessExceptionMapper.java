package org.app.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.openapitools.model.ErrorResponse;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<CustomException> {
  @Override
  public Response toResponse(CustomException exception) {
    ErrorResponse error = new ErrorResponse();
    error.setMessage(exception.getMessage());

    return Response.status(exception.getStatus())
        .entity(error)
        .build();
  }
}
