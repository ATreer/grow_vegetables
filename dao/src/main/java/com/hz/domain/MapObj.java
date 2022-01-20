package com.hz.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/20
 **/
@Data
@Table(name = "map_obj")
public class MapObj {
    @Id
    @KeySql(useGeneratedKeys = true)
    Integer id;
    @ColumnType(column = "map_id")
    Integer mapId;
    @ColumnType(column = "obj_id")
    Integer objId;
    @ColumnType(column = "obj_type")
    Integer objType;
}
