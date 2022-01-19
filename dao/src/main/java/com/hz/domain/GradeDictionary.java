package com.hz.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/17
 **/
@Data
@Table(name = "grade_dictionary")
public class GradeDictionary {
    @Id
    @KeySql(useGeneratedKeys = true)
    Integer id;
    String name;
    Integer type;
}
