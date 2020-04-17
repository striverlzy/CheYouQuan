package com.cyq.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Author：liuzhongyu
 * @Date: 2020/4/17 22:24
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class CarWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarWorkApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
