package com.hky.consumer.client;

import com.hky.consumer.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author iforeverhz
 */
@Component
public class UserClientImpl implements UserClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("用户查询出现异常！");
        return user;
    }
}
