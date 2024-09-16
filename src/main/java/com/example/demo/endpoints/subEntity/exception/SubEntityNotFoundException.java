package com.example.demo.endpoints.subEntity.exception;

public class SubEntityNotFoundException extends RuntimeException {
    public SubEntityNotFoundException(String id) {
        super("Not find subEntity with ID: " + id);
    }
}
