package com.niit.service.uploader;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ComponentScan("com.niit.service.uploader")
@EnableConfigurationProperties({UploaderProperties.class})
public class UploaderConfiguration {


}
