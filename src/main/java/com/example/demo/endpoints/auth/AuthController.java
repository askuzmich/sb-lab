package com.example.demo.endpoints.auth;

import com.example.demo.returnDataObject.CustomReturnData;
import com.example.demo.returnDataObject.CustomStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.endpoint.base-url}/users")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(
        AuthController.class
    );

    private final AuthService authService;

    public AuthController(AuthService authService) {
      this.authService = authService;
    }

    @PostMapping("/login")
    public CustomReturnData getLoginInfo(Authentication authentication) {
      LOGGER.debug("AuthUser: '{}'", authentication.getName());

      return new CustomReturnData(
           true,
          CustomStatusCode.SUCCESS,
          "Login user info and JWT",
          this.authService.getLoginInfo(authentication) // authentication.getName()
      );
    }
}
