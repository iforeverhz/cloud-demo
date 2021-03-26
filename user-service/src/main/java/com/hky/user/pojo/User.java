package com.hky.user.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: iforeverhz
 * @Date: 2020/5/27  20:22
 * <p>
 * 实体类 对应数据库中的user表
 * get&set toString 方法
 */

@Table(name = "user")
@Data
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private Integer age;
}
