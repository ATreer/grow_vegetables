package com.hz.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/19
 **/
@Data
@Table(name = "grow_props")
public class GrowProps {
    @Id
    @KeySql(useGeneratedKeys = true)
    Integer id;
    String name;
    @ColumnType(column = "grow_result_id")
    Integer growResultId;
    @ColumnType(column = "grow_cycle")
    long growCycle;
    Integer grade;
    Integer yield;
    Integer quality;
}
