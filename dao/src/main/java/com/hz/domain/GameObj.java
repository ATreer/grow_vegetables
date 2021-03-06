package com.hz.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/17
 **/
@Data
@Table(name = "game_obj")
public class GameObj {
    @Id
    @KeySql(useGeneratedKeys = true)
    Integer id;
    String name;
    @ColumnType(column = "`desc`")
    String desc;
    Integer grade;
    Integer type;
    @ColumnType(column = "image_url")
    String imageUrl;
}
