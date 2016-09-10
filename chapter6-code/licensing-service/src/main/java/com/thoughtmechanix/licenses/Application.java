package com.thoughtmechanix.licenses;

import com.thoughtmechanix.licenses.utils.UserContextFilter;
import com.thoughtmechanix.licenses.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class Application {
    @Bean
    public Filter userContextFilter() {
        UserContextFilter userContextFilter = new UserContextFilter();
        return userContextFilter;
    }

    @Primary
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
