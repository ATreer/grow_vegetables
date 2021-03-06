package com.hz.vegetable.controller;

import com.hz.domain.GrowPlantArea;
import com.hz.domain.complex.PlantAreObj;
import com.hz.vegetable.domain.CropResponseResult;
import com.hz.vegetable.service.GrowPlantAreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@RestController
@RequestMapping("plantArea")
public class PlantAreaController {

    @Resource
    GrowPlantAreaService growPlantAreaService;
    @PostMapping("findAll")
    public CropResponseResult<List<PlantAreObj>> findAllById(@RequestBody GrowPlantArea area){
        if (area.getGrowerId() == 0 ){
            return CropResponseResult.failed(10001,"参数合法");
        }
        return CropResponseResult.ok(growPlantAreaService.find(area.getGrowerId()));
    }

    @PostMapping("mature")
    public CropResponseResult<String> mature(@RequestBody GrowPlantArea area){
        if (area.getGrowerId() == 0 ){
            return CropResponseResult.failed(10001,"参数合法");
        }
        growPlantAreaService.mature(area);
        return CropResponseResult.ok(null);
    }

    @PostMapping("catchInsect")
    public CropResponseResult<String> catchInsect(@RequestBody GrowPlantArea area,@RequestParam int dealStatus){
        if (area.getGrowerId() == 0 || area.getId() == 0){
            return CropResponseResult.failed(10001,"参数合法");
        }
        System.out.println(" dealStatus  is " + dealStatus);
        growPlantAreaService.catchInsect(area,dealStatus);
        return CropResponseResult.ok(null);
    }
}
