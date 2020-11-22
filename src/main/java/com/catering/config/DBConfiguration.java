package com.catering.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
public class DBConfiguration {

    private static Logger logger = LoggerFactory.getLogger(DBConfiguration.class);

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        logger.info("DB connection for dev - H2");
        logger.info(driverClassName);
        logger.info(url);
        logger.info(username);
        logger.info(password);
        return "DB Connection for DEV - H2";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        logger.info("DB Connection for prod - MySql");
        logger.debug(driverClassName);
        logger.debug(url);
        return "DB Connection for MySql";
    }

}
