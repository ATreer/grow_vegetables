package com.hz.mapper;

import com.hz.domain.complex.PackageObj;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;
import com.hz.domain.Package;

import java.util.List;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
public interface PackageMapper extends Mapper<Package>, BaseInsertMapper<Package>, InsertListMapper<Package> {
    /**
     *  查询
     * @date 2022-01-18 11:11:17
     * @param id
     * @return  {@link List<PackageObj>}
     **/
    List<PackageObj> selectAllByUserId(int id);
}
