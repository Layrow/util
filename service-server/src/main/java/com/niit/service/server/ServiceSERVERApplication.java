package com.niit.service.server;

import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableSpringBootMetricsCollector
public class ServiceSERVERApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSERVERApplication.class, args);
    }
}
