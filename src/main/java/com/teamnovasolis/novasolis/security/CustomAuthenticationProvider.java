package com.teamnovasolis.novasolis.security;

import com.teamnovasolis.novasolis.user.DefaultUserService;
import com.teamnovasolis.novasolis.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private DefaultUserService defaultUserService;

    @Autowired
    public void setDefaultUserService(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserEntity user = defaultUserService.getUserByEmail(name);

        if (user != null) {

            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
                    user, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
