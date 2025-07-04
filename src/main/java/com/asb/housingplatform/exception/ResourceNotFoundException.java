package com.asb.housingplatform.exception;

public class ResourceNotFoundException extends RuntimeException  {
    public ResourceNotFoundException(String message) {
         super(message);
    }
}
