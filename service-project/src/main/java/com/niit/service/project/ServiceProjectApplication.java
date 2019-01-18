package com.niit.service.project;

import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName ServiceProjectApplication
 * @Description
 * @Author liyuhao
 * @Date 2018/12/18 15:50
 **/
@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableSpringBootMetricsCollector
@MapperScan("com.niit.service.project.dao")
public class ServiceProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProjectApplication.class, args);
    }
}
