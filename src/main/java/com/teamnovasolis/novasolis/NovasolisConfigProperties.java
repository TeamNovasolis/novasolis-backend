package com.teamnovasolis.novasolis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "novasolis")
@ConstructorBinding
public record NovasolisConfigProperties(String jwtSecret, Long jwtExpire) {

}