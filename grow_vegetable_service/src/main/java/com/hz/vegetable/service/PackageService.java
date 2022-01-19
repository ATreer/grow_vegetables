package com.hz.vegetable.service;

import com.hz.domain.GrowPlantArea;
import com.hz.domain.GrowProps;
import com.hz.domain.Package;
import com.hz.domain.complex.PackageObj;
import com.hz.mapper.GrowPropsMapper;
import com.hz.mapper.PackageMapper;
import com.hz.vegetable.domain.UseObjParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    GrowPropsMapper growPropsMapper;
    @Resource
    GrowPlantAreaService growPlantAreaService;
    public List<PackageObj> findAll(int growerId){
        return packageMapper.selectAllByUserId(growerId);
    }

    @Transactional(rollbackFor= Exception.class)
    public String plantObj(UseObjParam param, GrowPlantArea area){
        Example example = new Example(Package.class);
        example.createCriteria().andEqualTo("growerId",param.getGrowerId())
                .andEqualTo("objId",param.getObjId());
        List<Package> result = packageMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(result)){
            return "种子不存在。";
        }
        Package backpack = result.get(0);
        if (backpack.getObjCount() < param.getUseObjNum()){
            return "种子数量不正确。";
        }
        int lastCount = backpack.getObjCount() - param.getUseObjNum();
        example.createCriteria().andEqualTo("id",backpack.getId());
        if (lastCount == 0){
            packageMapper.deleteByPrimaryKey(backpack.getId());
        }else {
            backpack.setObjCount(lastCount);
            packageMapper.updateByPrimaryKey(backpack);
        }
        Example exampleProps = new Example(GrowProps.class);
        exampleProps.createCriteria().andEqualTo("id",param.getObjId());
        GrowProps props = growPropsMapper.selectOneByExample(exampleProps);
        area.setGameObjId(param.getObjId());
        area.setGrowCycle(props.getGrowCycle());
        area.setYield(props.getYield());
        growPlantAreaService.updateById(area);
        return "";
    }
}
