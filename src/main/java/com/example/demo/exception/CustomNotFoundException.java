package com.example.demo.exception;

public class CustomNotFoundException extends RuntimeException{

    public CustomNotFoundException(String entityName, String id) {
        super("Not find " + entityName + " with ID: " + id);
    }

    public CustomNotFoundException(String entityName, Integer id) {
        super("Not find " + entityName + " with ID: " + id);
    }

}
