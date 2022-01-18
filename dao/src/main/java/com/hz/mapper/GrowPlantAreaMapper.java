package com.hz.mapper;

import com.hz.domain.GrowPlantArea;
import com.hz.domain.complex.PlantAreObj;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
public interface GrowPlantAreaMapper extends Mapper<GrowPlantArea>, BaseInsertMapper<GrowPlantArea>, InsertListMapper<GrowPlantArea> {
    /**
     * 查询
     * @author HuangZ
     * @date 2022-01-18 04:50:36
     * @param id 用户id
     * @return  {@link List<PlantAreObj>}
     **/
    List<PlantAreObj> selectAllByUserId(int id);
}
