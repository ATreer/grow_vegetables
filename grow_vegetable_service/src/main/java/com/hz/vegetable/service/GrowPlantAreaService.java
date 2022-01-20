package com.hz.vegetable.service;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hz.domain.GrowPlantArea;
import com.hz.domain.Package;
import com.hz.domain.complex.GrowPropsObj;
import com.hz.domain.complex.PlantAreObj;
import com.hz.mapper.GrowPlantAreaMapper;
import com.hz.mapper.GrowPropsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Value("${plant.status.generation_probability}")
    float generationProbability;
    private final static int DEFAULT_BASE_NUM = 1000;
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
        int abc = RandomUtil.randomInt(DEFAULT_BASE_NUM);
        if (abc <= Math.round(generationProbability*DEFAULT_BASE_NUM) -1){
            result = result.stream()
                    .peek(item -> {
                        if ((item.getPlantTime()*1000 + item.getGrowCycle()*1000) > System.currentTimeMillis()
                            && StringUtils.isEmpty(item.getStatus())
                        ){
                            List<Integer> list = new ArrayList<>();
                            int index =RandomUtil.randomInt(10);
                            if (index >= 3 && index < 6){
                                list.add(10001);
                            }else if (index >= 6 && index < 8){
                                list.add(10002);
                            }else if (index >= 8){
                                list.add(10001);
                                list.add(10002);
                            }
                            item.setStatus(JSONObject.toJSONString(list));
                            if (!CollectionUtils.isEmpty(list)){
                                GrowPlantArea growPlantArea = new GrowPlantArea();
                                BeanUtils.copyProperties(item,growPlantArea);
                                growPlantAreaMapper.updateByPrimaryKey(growPlantArea);
                            }
                        }
                    }).collect(Collectors.toList());
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

    public void catchInsect(GrowPlantArea area,int status){
        GrowPlantArea result = findOne(area.getGrowerId(),area.getId());
        if (null == result){
            return;
        }
        JSONArray statusList = JSONObject.parseArray(result.getStatus());
        statusList.fluentRemove(status);
        String statusStr = "";
        if (statusList.size() != 0){
            statusStr = statusList.toJSONString();
        }
        area.setStatus(statusStr);
        area.setYield(area.getYield() + 1);
        updateById(area);
    }
}
