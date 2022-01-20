package com.hz.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/20
 **/
@Data
@Table(name = "adventure_map")
public class AdventureMap {
    @Id
    @KeySql(useGeneratedKeys = true)
    Integer id;
    String name;
    String desc;
    Integer grade;
    Integer type;
}
