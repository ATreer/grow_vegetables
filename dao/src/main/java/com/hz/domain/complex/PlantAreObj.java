package com.hz.domain.complex;

import lombok.Data;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@Data
public class PlantAreObj {
    Integer id;
    Integer growerId;
    String defaultName;
    String defaultDesc;
    Integer gameObjId;
    Integer yield;
    long plantTime;
    String name;
    String desc;
    Integer grade;
    Integer type;
    String imageUrl;
    String status;
    long growCycleTime;
    long growCycle;
}
