package com.anderson.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.anderson.adapter")
@EnableJpaRepositories(basePackages = "com.anderson.adapter.database.repository")
@EntityScan(basePackages = "com.anderson.adapter.database.entity")
public class ConfigInfrastructureBean {
}
