package com.teamnovasolis.novasolis.user;

import com.teamnovasolis.novasolis.user.exceptions.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        return userEntityRepository.findByEmail(email);
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

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = userEntityRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user.getRole()));
    }

    private static List<GrantedAuthority> getAuthorities (String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
