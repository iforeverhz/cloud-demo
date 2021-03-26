package com.hky.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author iforeverhz
 */
//日志级别
@Configuration
public class FeignConfig {
   @Bean
    Logger.Level feignLoggerLevel(){
       return Logger.Level.FULL;
   }
}