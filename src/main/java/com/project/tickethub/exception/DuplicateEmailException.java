package com.project.tickethub.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
//Why a custom exception?
//
//If someone registers with an existing email, we want to communicate a meaningful error rather than return a generic server error.
//
//Later, our global exception handler will convert this into HTTP 409 Conflict.