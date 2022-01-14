package com.hz.vegetable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author HuangZ
 */
@SpringBootApplication
@MapperScan({"com.hz.mapper"})
public class GrowVegetableApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowVegetableApplication.class, args);
    }

}
