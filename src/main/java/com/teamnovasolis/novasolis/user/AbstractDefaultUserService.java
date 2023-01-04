package com.teamnovasolis.novasolis.user;

import com.teamnovasolis.novasolis.user.exceptions.EmailExistsException;

public interface AbstractDefaultUserService {

    /**
     * Gets the user with the given id.
     */
    UserEntity getUserById(int id);

    /**
     * Gets the user with the given email.
     */
    UserEntity getUserByEmail(String email);

    UserEntity registerNewUserAccount(UserEntityDto user) throws EmailExistsException;
}
