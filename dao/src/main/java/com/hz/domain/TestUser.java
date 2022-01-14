package com.hz.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
@Data
@Table(name = "test_user")
public class TestUser {
    @Id
    @KeySql(useGeneratedKeys = true)
    Integer id;
    String name;
}
