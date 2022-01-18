package com.hz.domain.complex;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@Data
public class PackageObj {
    int id;
    int growerId;
    int maxPackageCount;
    int objId;
    int objCount;
    int objType;
    String name;
    String desc;
    int grade;
    int type;
    String imageUrl;
}
