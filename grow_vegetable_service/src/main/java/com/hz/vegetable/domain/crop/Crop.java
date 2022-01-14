package com.hz.vegetable.domain.crop;

import com.hz.vegetable.domain.Plant;
import com.hz.vegetable.domain.crop.type.CropQuality;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class Crop extends Plant {
    CropQuality quality;
    /**
     * 时间单位秒
     **/
    long growTime;
    /**
     * 生长周期，种子，抽芽，青禾，开花，抽穗，枯萎
     **/
    int growCycle;
    /**
     * 开花次数（青禾->开花->抽穗）
     **/
    int flowerTime;
    /**
     * 产量
     **/
    int yield;

    CropFruit fruit;
}
