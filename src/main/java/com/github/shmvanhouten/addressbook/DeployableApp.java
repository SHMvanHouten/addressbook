package com.github.shmvanhouten.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeployableApp {

    public static void main(String[] args) {
        SpringApplication.run(DeployableApp.class, args);
    }

}
