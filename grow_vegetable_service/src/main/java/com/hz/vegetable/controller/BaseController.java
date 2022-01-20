package com.hz.vegetable.controller;

import com.hz.vegetable.domain.Grower;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/20
 **/
public class BaseController {
    static Grower grower = new Grower();
    public Grower getGrower(){
        return grower;
    }
}
