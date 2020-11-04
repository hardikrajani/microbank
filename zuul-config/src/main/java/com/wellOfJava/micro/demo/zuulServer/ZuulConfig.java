package com.wellOfJava.micro.demo.zuulServer;

import com.wellOfJava.micro.demo.zuulServer.filter.ErrorFilter;
import com.wellOfJava.micro.demo.zuulServer.filter.PostFilter;
import com.wellOfJava.micro.demo.zuulServer.filter.PreFilter;
import com.wellOfJava.micro.demo.zuulServer.filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulConfig {
    public static void main(String[] args) {
        SpringApplication.run(ZuulConfig.class, args);
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
}
