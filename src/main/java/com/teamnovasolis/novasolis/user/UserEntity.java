package com.teamnovasolis.novasolis.user;

import com.teamnovasolis.novasolis.validators.ValidEmail;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Table(name = "NovasolisUser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "userId")
    private int id;

    @ValidEmail
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

    public UserEntity(UserEntityDto userEntityDto) {
        this.email = userEntityDto.getEmail();
        this.username = userEntityDto.getUsername();
        this.password = userEntityDto.getPassword();
    }
}