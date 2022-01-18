package com.hz.vegetable.domain;

import lombok.Data;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@Data
public class UseObjParam {
    int growerId;
    int objId;
    int objType;
    int targetId;
    int useObjNum;
}
