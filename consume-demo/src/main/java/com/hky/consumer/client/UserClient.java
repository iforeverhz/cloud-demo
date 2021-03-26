package com.hky.consumer.client;

import com.hky.consumer.config.FeignConfig;
import com.hky.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author iforeverhz
 */
@FeignClient(value = "user-service",fallback = UserClientImpl.class,configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/user/{id}")
    User queryById(@PathVariable("id") Long id);
}
