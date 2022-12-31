package com.teamnovasolis.novasolis;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("novasolis")
public record NovasolisConfigProperties(String jwtSecret) {

}