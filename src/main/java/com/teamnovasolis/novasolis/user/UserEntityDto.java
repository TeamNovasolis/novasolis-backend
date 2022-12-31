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
    private String userId;
    private String email;
    private String password;
    private String role;
    private String createdOn;
}
