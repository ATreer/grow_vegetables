package com.hz.mapper;

import com.hz.domain.GameObj;
import com.hz.domain.GrowProps;
import com.hz.domain.complex.GrowPropsObj;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
public interface GrowPropsMapper extends Mapper<GrowProps>, BaseInsertMapper<GrowProps>, InsertListMapper<GrowProps> {
    /**
     * 级联查询
     * @author HuangZ
     * @date 2022-01-19 04:25:43
     * @param id 查询id
     * @return  {@link GrowPropsObj}
     **/
    GrowPropsObj selectAllById(int id);
}
