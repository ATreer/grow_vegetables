package com.hz.mapper;

import com.hz.domain.GradeDictionary;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
public interface GradeDictionaryMapper extends Mapper<GradeDictionary>, BaseInsertMapper<GradeDictionary>, InsertListMapper<GradeDictionary> {
}
