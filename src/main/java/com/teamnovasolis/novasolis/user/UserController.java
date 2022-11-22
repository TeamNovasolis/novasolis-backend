package com.teamnovasolis.novasolis.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private DefaultUserService defaultUserService;

    @Autowired
    public void setDefaultUserService(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntityDto userDto) {
        UserEntity user = new UserEntity(userDto);
        return defaultUserService.registerNewUserAccount(user);
    }
}
