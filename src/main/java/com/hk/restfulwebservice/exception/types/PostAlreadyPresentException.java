package com.hk.restfulwebservice.exception.types;

public class PostAlreadyPresentException extends RuntimeException {
    public PostAlreadyPresentException(String message) {
        super(message);
    }
}
