package com.tensquare.sms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;

/**
 * @Author：liuzhongyu
 * @Date: 2020/3/29 21:27
 * @Description:
 */
@Configuration
public class RabbitMQConfig {
    //direct模式，直接根据队列名称投递消息
//    @Bean
//    public Queue logOpQueue() {
//        return new Queue("oplog");
//    }
//
//    @Bean
//    public Queue logErrQueue() {
//        return new Queue("errlog");
//    }
//
//    //不要偷懒 一定要声明
//    @Bean
//    public Queue chatMessageQueue() {
//        return new Queue("chatMessage");
//    }
}
