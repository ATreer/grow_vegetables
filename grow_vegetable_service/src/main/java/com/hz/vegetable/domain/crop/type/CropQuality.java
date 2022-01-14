package com.hz.vegetable.domain.crop.type;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
public enum CropQuality {
    /**
     *  1：瘦弱的，2 普通，3，稀有，4仙植
     **/
    EMACIATED(1,"瘦弱"),
    SIMPLE(2,"普通"),
    RARE(3,"稀有"),
    IMMORTAL(4,"仙植");
    int code;
    String name;

    CropQuality(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
