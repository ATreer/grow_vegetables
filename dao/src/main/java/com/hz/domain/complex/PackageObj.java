package com.hz.domain.complex;

import lombok.Data;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@Data
public class PackageObj {
    Integer id;
    Integer growerId;
    Integer maxPackageCount;
    Integer objId;
    Integer objCount;
    Integer objType;
    String name;
    String desc;
    Integer grade;
    Integer type;
    String imageUrl;
}
