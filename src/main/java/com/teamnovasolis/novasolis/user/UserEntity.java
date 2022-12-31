package com.teamnovasolis.novasolis.user;

import com.teamnovasolis.novasolis.validators.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "NovasolisUser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "userId")
    private int id;

    @ValidEmail
    @NotEmpty(message = "E-Mail is required")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role = "USER";

    @Column(name = "created_on")
    private Date createdOn = new Date(System.currentTimeMillis());

    public UserEntity(UserEntityDto userEntityDto) {
        this.email = userEntityDto.getEmail();
        this.password = userEntityDto.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}