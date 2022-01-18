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
@Table(name = "package")
public class Package {
    @Id
    @KeySql(useGeneratedKeys = true)
    int id;
    @ColumnType(column = "grower_id")
    int growerId;
    @ColumnType(column = "max_package_count")
    int maxPackageCount;
    @ColumnType(column = "obj_id")
    int objId;
    @ColumnType(column = "obj_count")
    int objCount;
    @ColumnType(column = "obj_type")
    int objType;
}
