package com.wellOfJava.micro.demo.transactionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.wellOfJava.micro.demo.commons.*")
public class TransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }
}

