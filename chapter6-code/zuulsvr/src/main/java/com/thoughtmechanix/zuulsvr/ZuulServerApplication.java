package com.thoughtmechanix.zuulsvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import com.thoughtmechanix.zuulsvr.filters.TrackingFilter;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServerApplication {

    @Bean
    public TrackingFilter trackingFilter(){
        return new TrackingFilter();
    }
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}

//@Might need to enable @EnableZuulServer to Inject my bean
//http://stackoverflow.com/questions/35966071/dynamic-proxying-with-zuul
