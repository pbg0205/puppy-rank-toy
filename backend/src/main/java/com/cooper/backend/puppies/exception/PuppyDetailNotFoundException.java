package com.cooper.backend.puppies.exception;

public class PuppyDetailNotFoundException extends IllegalArgumentException {

    public PuppyDetailNotFoundException(String message) {
        super(message);
    }

}
