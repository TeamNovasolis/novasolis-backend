package com.teamnovasolis.novasolis.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserEntityDto {
    private String email;
    private String username;
    private String password;
}
