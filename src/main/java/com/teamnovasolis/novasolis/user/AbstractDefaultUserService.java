package com.teamnovasolis.novasolis.user;

import com.teamnovasolis.novasolis.user.exceptions.EmailExistsException;

public interface AbstractDefaultUserService {

    /**
     * Gets the user with the given id.
     */
    UserEntity getUserById(int id);

    /**
     * Gets the user with the given username.
     */
    UserEntity getUserByUsername(String username);

    /**
     * Gets the user with the given email.
     */
    UserEntity getUserByEmail(String email);

    UserEntity registerNewUserAccount(UserEntity user) throws EmailExistsException;
}
