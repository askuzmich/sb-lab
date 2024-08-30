package com.example.demo.axceptionHandler;

import com.example.demo.endpoints.subEntity.SubEntityNotFoundException;
import com.example.demo.returnDataObject.CustomReturnData;
import com.example.demo.returnDataObject.CustomStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(SubEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    CustomReturnData subEntityNotFoundExc(SubEntityNotFoundException exc) {
        return new CustomReturnData(false, CustomStatusCode.NOT_FOUND, exc.getMessage());
    }
}
