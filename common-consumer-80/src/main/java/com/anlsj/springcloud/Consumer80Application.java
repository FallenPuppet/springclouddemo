package com.anlsj.springcloud;

import com.anlsj.ribbon.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="MICROSERVICE-DEPT",configuration = MySelfRule.class)
public class Consumer80Application {
    public static void main(String[] args) {
        SpringApplication.run(Consumer80Application.class, args);
    }
}
