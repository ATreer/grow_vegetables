package com.hz.vegetable.service;

import cn.hutool.core.util.RandomUtil;
import com.hz.domain.GameObj;
import com.hz.domain.GrowProps;
import com.hz.domain.MapObj;
import com.hz.domain.Package;
import com.hz.mapper.GameObjMapper;
import com.hz.mapper.GrowPropsMapper;
import com.hz.mapper.MapObjMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/20
 **/
@Service
public class AdventureService {
    @Resource
    MapObjMapper mapObjMapper;
    @Resource
    GameObjMapper gameObjMapper;
    @Resource
    GrowPropsMapper growPropsMapper;
    @Resource
    PackageService packageService;
    public List<Map<String,Object>> adventure(int mapId){
        Example example = new Example(MapObj.class);
        example.createCriteria().andEqualTo("mapId",mapId);
        List<MapObj> mapObjList = mapObjMapper.selectByExample(example);
        List<Map<String,Object>> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(mapObjList)){
            return result;
        }
        mapObjList.forEach(System.out::println);
        AtomicInteger LOOP_COUNT = new AtomicInteger(5);
        while (LOOP_COUNT.getAndDecrement() >= 0) {
            int index = RandomUtil.randomInt(10);
            Map<String,Object> item = new HashMap<>(8);
            if (index >= 5){
                continue;
            }
            if (index >= 3){
                MapObj obj = mapObjList.get(RandomUtil.randomInt(mapObjList.size()));
                Package backpack = new Package();
                backpack.setGrowerId(1);
                backpack.setObjId(obj.getObjId());
                backpack.setObjType(obj.getObjType());
                int count = obj.getObjType() == 9? 1: RandomUtil.randomInt(3)+1;
                backpack.setObjCount(count);
                backpack.setMaxPackageCount(20);
                item.put("count",count);
                if (obj.getObjType() == 9){
                    Example exampleProps = new Example(GrowProps.class);
                    exampleProps.createCriteria().andEqualTo("id",obj.getObjId());
                    GrowProps growProps = growPropsMapper.selectOneByExample(exampleProps);
                    item.put("name",growProps.getName());
                    item.put("desc","");
                }else {
                    Example exampleObj = new Example(GameObj.class);
                    exampleObj.createCriteria().andEqualTo("id",obj.getObjId());
                    GameObj gameObj = gameObjMapper.selectOneByExample(exampleObj);
                    item.put("name",gameObj.getName());
                    item.put("desc",gameObj.getDesc());
                }
                result.add(item);
                packageService.add(backpack);
            }
        }

        return result;
    }
}
