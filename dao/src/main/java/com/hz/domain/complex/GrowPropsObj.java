package com.hz.domain.complex;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/19
 **/
@Data
public class GrowPropsObj {
    Integer id;
    String name;
    Integer growResultId;
    long growCycle;
    Integer grade;
    Integer yield;
    Integer quality;
    String objName;
    String desc;
    Integer type;
    String imageUrl;
}
