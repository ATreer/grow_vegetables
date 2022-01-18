package com.hz.domain.complex;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@Data
public class PlantAreObj {
    int id;
    int growerId;
    String defaultName;
    String defaultDesc;
    int gameObjId;
    String name;
    String desc;
    int grade;
    int type;
    String imageUrl;
    String status;
    long growCycleTime;
    long growCycle;
}
