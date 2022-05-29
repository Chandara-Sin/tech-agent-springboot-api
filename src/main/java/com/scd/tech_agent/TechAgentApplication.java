package com.scd.tech_agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan({"com.scd.tech_agent.controller","com.scd.tech_agent.service", "com.scd.tech_agent.model.mapper",
//        "com.scd.tech_agent.model.dto","com.scd.tech_agent.util","com.scd.tech_agent.repository",
////        "com.scd.tech_agent.entity","com.scd.tech_agent.model"})
//@EnableJpaRepositories("com.scd.tech_agent.repository")
//@EntityScan("com.scd.tech_agent.entity")
@SpringBootApplication
public class TechAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechAgentApplication.class, args);
    }

}
