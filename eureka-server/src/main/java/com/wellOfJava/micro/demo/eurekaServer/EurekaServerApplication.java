package com.wellOfJava.micro.demo.eurekaServer;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    /*@Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-webAllowOthers", "-tcpAllowOthers", "-tcpPort", "9090");
    }*/
}
