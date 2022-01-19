package com.hz.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@Data
@Table(name ="grow_plant_area")
public class GrowPlantArea {
    @Id
    @KeySql(useGeneratedKeys = true)
    Integer id;
    @ColumnType(column = "grower_id")
    Integer growerId;
    @ColumnType(column = "default_name")
    String defaultName;
    @ColumnType(column = "default_desc")
    String defaultDesc;
    @ColumnType(column = "game_obj_id")
    Integer gameObjId;
    String status;
    @ColumnType(column = "grow_cycle_time")
    Long growCycleTime;
    @ColumnType(column = "plant_time")
    Long plantTime;
    @ColumnType(column = "grow_cycle")
    Long growCycle;
    Integer yield;
}
