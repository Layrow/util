package com.niit.service.lms;

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

/**
 * @Description: java类作用描述
 * @Author: yuwentao
 * @CreateDate: 2018.11.08 10:51
 * @UpdateUser: yuwentao
 * @UpdateDate: 2018.11.08 10:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */

public class ServiceLMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLMSApplication.class, args);
    }
}
