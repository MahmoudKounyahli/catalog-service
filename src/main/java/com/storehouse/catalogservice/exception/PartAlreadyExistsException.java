package com.storehouse.catalogservice.exception;

public class PartAlreadyExistsException extends RuntimeException {
    public PartAlreadyExistsException(String partNumber) {
        super("A Part with part number " + partNumber + " already exists.");
    }
}
