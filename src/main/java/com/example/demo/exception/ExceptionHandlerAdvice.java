package com.example.demo.exception;

//import com.example.demo.endpoints.headObject.exc.HeadObjectNotFoundException;
//import com.example.demo.endpoints.subEntity.exception.SubEntityNotFoundException;
import com.example.demo.returnDataObject.CustomReturnData;
import com.example.demo.returnDataObject.CustomStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomReturnData notFoundExc(CustomNotFoundException exc) {
        return new CustomReturnData(false, CustomStatusCode.NOT_FOUND, exc.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomReturnData controllerAddValidation(MethodArgumentNotValidException exc) {
        List<ObjectError> errors = exc.getBindingResult().getAllErrors();

        Map<String, String> map = new HashMap<>(errors.size());

        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String value = error.getDefaultMessage();
            map.put(key, value);
        });

        return new CustomReturnData(
                false,
                CustomStatusCode.INVALID_ARGUMENT,
                "see what wrong in data",
                map
        );
    }

    @ExceptionHandler({
        UsernameNotFoundException.class,
        BadCredentialsException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    CustomReturnData userOAuthExc(Exception exc) {
        return new CustomReturnData(
            false,
            CustomStatusCode.UNAUTHORIZED,
            "Wrong Username or Password",
            exc.getMessage()
        );
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    CustomReturnData userLoginExc(InsufficientAuthenticationException exc) {
        return new CustomReturnData(
            false,
            CustomStatusCode.UNAUTHORIZED,
            "Missing login credentials",
            exc.getMessage()
        );
    }

    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    CustomReturnData accountStatusOAuthExc(AccountStatusException exc) {
        return new CustomReturnData(
            false,
            CustomStatusCode.UNAUTHORIZED,
            "Disabled or Blocked User Account (Account Status)",
            exc.getMessage()
        );
    }

    @ExceptionHandler(InvalidBearerTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    CustomReturnData bearerTokenOAuthExc(InvalidBearerTokenException exc) {
        return new CustomReturnData(
            false,
            CustomStatusCode.UNAUTHORIZED,
            "Invalid Bearer Token (expired, revoked, malformed...)",
            exc.getMessage()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    CustomReturnData accessDeniedOAuthExc(AccessDeniedException exc) {
        return new CustomReturnData(
            false,
            CustomStatusCode.FORBIDDEN,
            "Access Denied. No permission",
            exc.getMessage()
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomReturnData notFoundExc(NoHandlerFoundException exc) {
        return new CustomReturnData(
            false,
            CustomStatusCode.NOT_FOUND,
            "Not Found",
            exc.getMessage()
        );
    }

    /**
     * All Other Above Error was not triggered
     * @param exc
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    CustomReturnData allExc(Exception exc) {
        return new CustomReturnData(
            false,
            CustomStatusCode.INTERNAL_SERVER_ERROR,
            "Internal Server Error",
            exc.getMessage()
        );
    }


}
