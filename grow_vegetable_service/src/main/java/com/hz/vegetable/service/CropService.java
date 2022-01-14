package com.hz.vegetable.service;


import com.hz.vegetable.domain.crop.Crop;
import org.springframework.stereotype.Service;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
@Service
public class CropService {

    public Crop getCropInfo(){
        return new Crop();
    }


}
