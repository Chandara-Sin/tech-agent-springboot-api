package com.scd.tech_agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.scb.tech_agent.service", "com.scb.tech_agent.controller", "com.scb.tech_agent.model",
        "com.scb.tech_agent.entity", "com.scb.tech_agent.config", "com.scb.tech_agent.util",
        "com.scb.tech_agent.exception"})
@SpringBootApplication
public class TechAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechAgentApplication.class, args);
    }

}
