package com.teamnovasolis.novasolis.user.exceptions;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(String email) {
        super("There is no user with email: " + email);
    }
}
