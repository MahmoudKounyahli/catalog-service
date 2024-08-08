package com.storehouse.catalogservice.exception;

public class PartNotFoundException extends RuntimeException {
    public PartNotFoundException(String partNumber) {
        super("The part with number " + partNumber + " was not found.");
    }
}
