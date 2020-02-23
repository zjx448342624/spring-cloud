package com.zjx.spring.cloud.feign.person.service.provier;

import com.zjx.spring.cloud.feign.api.service.PersonService;
import com.zjx.spring.cloud.feign.api.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;

/**
 *
 */
@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
@Import(WebConfig.class)
public class PersonServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceProviderApplication.class,args);
    }

}
