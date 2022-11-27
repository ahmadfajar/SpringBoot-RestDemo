package com.fajarconsultant.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {
        "com.fajarconsultant.restdemo.config",
        "com.fajarconsultant.restdemo.controller",
        "com.fajarconsultant.restdemo.repository",
})
@ConfigurationPropertiesScan("com.fajarconsultant.restdemo.config")
@EntityScan("com.fajarconsultant.restdemo.entity")
public class RestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestDemoApplication.class, args);
    }

}
