package com.hz.vegetable.service;

import com.hz.domain.Package;
import com.hz.domain.complex.PackageObj;
import com.hz.mapper.PackageMapper;
import com.hz.vegetable.domain.UseObjParam;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@Service
public class PackageService {
    @Resource
    PackageMapper packageMapper;

    public List<PackageObj> findAll(int growerId){
        return packageMapper.selectAllByUserId(growerId);
    }

    public String plantObj (UseObjParam param){
        Example example = new Example(Package.class);
        example.createCriteria().andEqualTo("growerId",param.getGrowerId())
                .andEqualTo("objId",param.getObjId());
        List<Package> result = packageMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(result)){
            return "种子不存在。";
        }
        return "";
    }
}
