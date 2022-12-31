package com.teamnovasolis.novasolis.solispulse;

import com.teamnovasolis.novasolis.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolisPulseEntity {

    @Id
    private int solisPulseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotEmpty(message = "UserId is required")
    @JoinColumn(name = "userId")
    private UserEntity user;

    @NotEmpty(message = "ChipId is required")
    @Column(name = "chipId", nullable = false, unique = true)
    private String chipId;

    @Column(name = "created_on")
    private Date createdOn = new Date(System.currentTimeMillis());
}