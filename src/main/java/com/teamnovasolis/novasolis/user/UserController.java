package com.teamnovasolis.novasolis.user;

import com.teamnovasolis.novasolis.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenService jwtTokenService;
    private DefaultUserService defaultUserService;

    @Autowired
    public void setDefaultUserService(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    @PostMapping("/auth/register")
    public UserEntity registerUser(@RequestBody UserEntityDto userDto) {
        return defaultUserService.registerNewUserAccount(userDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserEntityDto userDto) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getEmail(), userDto.getPassword())
            );

            Object user = authentication.getPrincipal();
            //String accessToken = jwtTokenService.generateToken(user);

            return ResponseEntity.ok().body(user);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
