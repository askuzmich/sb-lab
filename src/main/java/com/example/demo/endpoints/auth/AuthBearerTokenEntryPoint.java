package com.example.demo.endpoints.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class AuthBearerTokenEntryPoint implements AuthenticationEntryPoint {

  private final HandlerExceptionResolver resolver;

  public AuthBearerTokenEntryPoint(
      @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver
  ) {
    this.resolver = resolver;
  }

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException
  ) throws IOException, ServletException {
    this.resolver.resolveException(request, response, null, authException);
  }
}
