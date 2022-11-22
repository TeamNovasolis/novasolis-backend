package com.teamnovasolis.novasolis.user;

import com.teamnovasolis.novasolis.user.exceptions.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements AbstractDefaultUserService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final UserEntityRepository userEntityRepository;

    public DefaultUserService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserEntity getUserById(int id) {
        return null;
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserEntity registerNewUserAccount(UserEntity userDto) throws EmailExistsException {
        if (getUserByEmail(userDto.getEmail()) != null) {
            throw new EmailExistsException("There is already an account with that email address: " + userDto.getEmail());
        }

        UserEntity user = new UserEntity();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userEntityRepository.save(user);
    }
}
