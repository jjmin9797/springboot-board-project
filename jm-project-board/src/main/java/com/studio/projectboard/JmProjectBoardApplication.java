package com.studio.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class JmProjectBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmProjectBoardApplication.class, args);
    }

}
