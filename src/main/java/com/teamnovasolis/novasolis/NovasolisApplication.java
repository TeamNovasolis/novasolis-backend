package com.teamnovasolis.novasolis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication()
@EnableConfigurationProperties(NovasolisConfigProperties.class)
public class NovasolisApplication {
	public static void main(String[] args) {
		SpringApplication.run(NovasolisApplication.class, args);
	}
}
