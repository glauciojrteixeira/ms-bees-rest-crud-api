package com.bees.configurations;

import com.bees.services.DatabaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("local")
public class ProfileLocalConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;


    @Bean
    public boolean instantiateDatabase(DatabaseService databaseService) throws ParseException {
        if (!"create-drop".equals(strategy)) {
            return false;
        }

        databaseService.instantiateDatabaseLocal();

        return true;
    }
}
