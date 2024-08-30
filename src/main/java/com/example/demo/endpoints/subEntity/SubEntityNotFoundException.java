package com.example.demo.endpoints.subEntity;

public class SubEntityNotFoundException extends RuntimeException {
    public SubEntityNotFoundException(String id) {
        super("Not find subEntity with ID: " + id);
    }
}
