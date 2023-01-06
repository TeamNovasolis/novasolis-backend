package com.teamnovasolis.novasolis.auth;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
}