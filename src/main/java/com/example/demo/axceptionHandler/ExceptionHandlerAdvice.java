package com.example.demo.axceptionHandler;

import com.example.demo.endpoints.subEntity.exception.SubEntityNotFoundException;
import com.example.demo.returnDataObject.CustomReturnData;
import com.example.demo.returnDataObject.CustomStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(SubEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomReturnData subEntityNotFoundExc(SubEntityNotFoundException exc) {
        return new CustomReturnData(false, CustomStatusCode.NOT_FOUND, exc.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomReturnData subEntityControllerAddValidation(MethodArgumentNotValidException exc) {
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
}
