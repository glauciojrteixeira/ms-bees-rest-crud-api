package com.bees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Value("${spring.profiles.active}")
    private String profileActive;
    @Value("${server.port}")
    private String serverPort;
    @Value("${swagger.enable}")
    private String swaggerEnable;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("-------------------------------------------------------------------------");
        LOGGER.info("Projeto .............: [ {} ]", "ms-bees-rest-crud-api");
        LOGGER.info("Versão Java .........: [ {} ]", System.getProperty("java.version"));
        LOGGER.info("OS Name .............: [ {} ]", System.getProperty("os.name"));
        LOGGER.info("OS Architecture .....: [ {} ]", System.getProperty("os.arch"));
        LOGGER.info("OS Version ..........: [ {} ]", System.getProperty("os.version"));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Perfil Ativo ........: [ {} ]", profileActive.toUpperCase());
        }
        LOGGER.info("URL .................: [ {} ]", "");
        LOGGER.info("Porta(s) ............: [ {} (http)]", serverPort);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Swagger .............: [ {} ]", swaggerEnable.toUpperCase());
        }

        LOGGER.info("-------------------------------------------------------------------------");
        LOGGER.info("ms-bees-rest-crud-api is up!\n");
    }
}