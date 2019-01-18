package com.niit.service.cms;

import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.niit.service.cms.dao")
public class ServiceCMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCMSApplication.class, args);
    }
}
