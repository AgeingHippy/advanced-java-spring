package com.codingnomads.springtest.usingtestresttemplate.exception;

public class CoffeePreferenceNotFound extends Exception {

    public CoffeePreferenceNotFound() {
        super();
    }

    public CoffeePreferenceNotFound(String message) {
        super(message);
    }
}
