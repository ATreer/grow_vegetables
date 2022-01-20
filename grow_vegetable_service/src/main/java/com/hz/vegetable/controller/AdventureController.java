package com.hz.vegetable.controller;

import com.hz.domain.AdventureMap;
import com.hz.domain.MapObj;
import com.hz.mapper.AdventureMapMapper;
import com.hz.mapper.MapObjMapper;
import com.hz.vegetable.domain.CropResponseResult;
import com.hz.vegetable.service.AdventureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/20
 **/
@RestController
@RequestMapping("adventure")
public class AdventureController extends BaseController{

    @Resource
    AdventureMapMapper adventureMapMapper;
    @Resource
    AdventureService adventureService;

    @PostMapping("getMapAll")
    public CropResponseResult<List<AdventureMap>> getMapAll(){
        return CropResponseResult.ok(adventureMapMapper.selectAll());
    }

    @PostMapping("adventure")
    public CropResponseResult<List<Map<String,Object>>> adventure(@RequestBody MapObj obj){
        return CropResponseResult.ok(adventureService.adventure(obj.getMapId()));
    }
}
