package com.teamnovasolis.novasolis.user;

import com.teamnovasolis.novasolis.user.exceptions.EmailExistsException;
import com.teamnovasolis.novasolis.user.exceptions.UserDoesNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
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
    public UserEntity getUserByEmail(String email) {
        return userEntityRepository.findByEmail(email).orElseThrow(() -> new UserDoesNotExistsException(email));
    }

    @Override
    public UserEntity registerNewUserAccount(UserEntityDto userDto) throws EmailExistsException {
        if (getUserByEmail(userDto.getEmail()) != null) {
            throw new EmailExistsException("There is already an account with that email address: " + userDto.getEmail());
        }

        UserEntity user = new UserEntity();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userEntityRepository.save(user);
    }
}
