package com.hz.vegetable.domain;

import lombok.Data;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/20
 **/
@Data
public class Grower {
    int id;
    String name;
    String account;
    int gender;
    int status;
    int grade;
    long gradeVal;
}
