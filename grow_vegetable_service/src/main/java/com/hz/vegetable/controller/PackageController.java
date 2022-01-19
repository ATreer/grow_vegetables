package com.hz.vegetable.controller;

import com.hz.domain.GrowPlantArea;
import com.hz.domain.Package;
import com.hz.domain.complex.PackageObj;
import com.hz.vegetable.domain.CropResponseResult;
import com.hz.vegetable.domain.UseObjParam;
import com.hz.vegetable.service.GrowPlantAreaService;
import com.hz.vegetable.service.PackageService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/17
 **/
@RestController
@RequestMapping("package")
public class PackageController {
    @Resource
    PackageService packageService;
    @Resource
    GrowPlantAreaService growPlantAreaService;
    @PostMapping("findAll")
    public CropResponseResult<List<PackageObj>> findAll(@RequestBody Package backpack){
        if (backpack.getGrowerId() == 0){
            return CropResponseResult.failed(10001,"参数不合法");
        }
        return CropResponseResult.ok(packageService.findAll(backpack.getGrowerId()));
    }

    @PostMapping("plantObj")
    public CropResponseResult<String> plantObj (@RequestBody UseObjParam param){
        //参数判空
        if(null == param || param.getObjId() == 0|| param.getGrowerId() == 0){
            return CropResponseResult.failed(10001,"参数不合法");
        }
        //检查土地
        GrowPlantArea area = growPlantAreaService.findOne(param.getGrowerId(),param.getTargetId());
        if (null == area){
            return CropResponseResult.failed(10001,"参数不合法");
        }
        //检查种子
        String message = packageService.plantObj(param,area);
        if (!StringUtils.isEmpty(message)){
            return CropResponseResult.failed(20001,message);
        }

        return CropResponseResult.ok("种植成功。");
    }
}
