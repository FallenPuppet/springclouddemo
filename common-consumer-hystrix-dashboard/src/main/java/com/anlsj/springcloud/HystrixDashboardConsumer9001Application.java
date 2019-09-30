package com.anlsj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardConsumer9001Application {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardConsumer9001Application.class, args);
    }
}
