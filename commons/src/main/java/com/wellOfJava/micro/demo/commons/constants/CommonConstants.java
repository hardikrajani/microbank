package com.wellOfJava.micro.demo.commons.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class CommonConstants {
    public static String account="http://ACCOUNT-SERVICES";
    public static String user="http://USER-SERVICES";
    public static String transaction="http://TRANSACTION-SERVICES";
}
