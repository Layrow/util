package com.niit.service.uploader;


import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: huangwei
 * @Date:2018/12/20 9:20
 * @Description:
 */
@SpringBootApplication
@EnableUploader
@EnableSwagger2
@EnableEurekaClient
@EnableHystrix
@EnableSpringBootMetricsCollector
public class UploaderApplication {
    public static void main(String[] args) { SpringApplication.run(UploaderApplication.class, args);}
}
