package com.teamnovasolis.novasolis.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Table(name = "NovasolisUser")
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "userId")
    private int id;

    @NotEmpty(message = "E-Mail is required")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Username is required")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_on")
    private Date createdOn = new Date(System.currentTimeMillis());
}