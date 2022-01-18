package com.hz.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;
import com.hz.domain.Package;
/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
public interface PackageMapper extends Mapper<Package>, BaseInsertMapper<Package>, InsertListMapper<Package> {
}
