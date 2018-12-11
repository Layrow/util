package com.niit.website.smartkids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.09 10:16
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.09 10:16
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */


@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class WebsiteSmartkids {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsiteSmartkids.class, args);
    }
}
