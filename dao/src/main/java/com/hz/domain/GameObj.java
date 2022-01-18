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
    int id;
    String name;
    String desc;
    int grade;
    int type;
    @ColumnType(column = "image_url")
    String imageUrl;
}
