package com.hz.vegetable.service;

import com.hz.domain.GrowPlantArea;
import com.hz.domain.Package;
import com.hz.domain.complex.GrowPropsObj;
import com.hz.domain.complex.PlantAreObj;
import com.hz.mapper.GrowPlantAreaMapper;
import com.hz.mapper.GrowPropsMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@Service
public class GrowPlantAreaService {
    @Resource
    GrowPlantAreaMapper growPlantAreaMapper;
    @Resource
    PackageService packageService;
    @Resource
    GrowPropsMapper growPropsMapper;
    @Value("${plant.area.initCount}")
    int initCount;
    public List<PlantAreObj> find(int growerId){
        List<PlantAreObj> result = growPlantAreaMapper.selectAllByUserId(growerId);
        if (CollectionUtils.isEmpty(result)){
            List<GrowPlantArea> insertList = new ArrayList<>();
            for (int i = 0; i < initCount; i++) {
                GrowPlantArea area = new GrowPlantArea();
                area.setDefaultName("空地");
                area.setDefaultDesc("空空如也的空地");
                area.setGrowerId(growerId);
                insertList.add(area);
            }
            growPlantAreaMapper.insertList(insertList);
            result = growPlantAreaMapper.selectAllByUserId(growerId);
        }
        return result;
    }

    public GrowPlantArea findOne(int growerId,int id){
        Example example = new Example(GrowPlantArea.class);
        example.createCriteria().andEqualTo("growerId",growerId).andEqualTo("id",id);
        List<GrowPlantArea> result = growPlantAreaMapper.selectByExample(example);
        Optional<GrowPlantArea> plantAreaList =  result.stream().findFirst();
        return plantAreaList.orElse(null);
    }

    public void updateById(GrowPlantArea area){
        growPlantAreaMapper.updateByPrimaryKey(area);
    }

    @Transactional(rollbackFor = Exception.class)
    public void mature(GrowPlantArea area){
        Example example = new Example(GrowPlantArea.class);
        example.createCriteria().andEqualTo("growerId",area.getGrowerId());
        GrowPlantArea result = findOne(area.getGrowerId(),area.getId());
        if (null == result){
            return;
        }
        GrowPropsObj growProps = growPropsMapper.selectAllById(result.getGameObjId());
        Package packObj = new Package();
        packObj.setObjId(growProps.getGrowResultId());
        packObj.setGrowerId(result.getGrowerId());
        //TODO
        packObj.setMaxPackageCount(20);
        packObj.setObjCount(result.getYield());
        packObj.setObjType(growProps.getType());
        packageService.add(packObj);
        area.setPlantTime(0L);
        area.setGrowCycle(0L);
        area.setGameObjId(0);
        area.setGrowCycleTime(0L);
        area.setStatus("");
        area.setYield(0);
        updateById(area);
    }
}
