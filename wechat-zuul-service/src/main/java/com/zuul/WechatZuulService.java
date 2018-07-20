package com.zuul;

import com.zuul.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class WechatZuulService {

    public static void main(String[] args) {
        SpringApplication.run(WechatZuulService.class,args);
    }

    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }

}
