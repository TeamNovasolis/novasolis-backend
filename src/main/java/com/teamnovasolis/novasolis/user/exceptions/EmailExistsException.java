package com.teamnovasolis.novasolis.user.exceptions;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String email) {
        super("There is already an account with that email address: " + email);
    }
}
