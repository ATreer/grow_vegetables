package com.hz.vegetable.domain;

import lombok.Data;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/18
 **/
@Data
public class CropResponseResult<T> {
    int code;
    String message;
    T data;
    public static <T>CropResponseResult<T> ok(T data){
        CropResponseResult<T> responseResult = new CropResponseResult<>();
        responseResult.setCode(0);
        responseResult.setMessage("success");
        responseResult.setData(data);
        return responseResult;
    }

    public static <T>CropResponseResult<T> failed(int code ,String message){
        CropResponseResult<T> responseResult = new CropResponseResult<>();
        responseResult.setCode(code);
        responseResult.setMessage(message);
        responseResult.setData(null);
        return responseResult;
    }

}
