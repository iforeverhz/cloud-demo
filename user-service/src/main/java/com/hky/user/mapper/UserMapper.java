package com.hky.user.mapper;

import com.hky.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.security.PrivateKey;

/**
 * @author iforeverhz
 */
public interface UserMapper extends Mapper<User> {
}
