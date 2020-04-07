package com.tensquare.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Author：liuzhongyu
 * @Date: 2019/12/14 09:38
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
