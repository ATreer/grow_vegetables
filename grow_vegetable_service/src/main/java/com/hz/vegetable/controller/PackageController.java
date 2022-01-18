package com.hz.vegetable.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hz.domain.Package;
import com.hz.mapper.PackageMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    PackageMapper packageMapper;

    @PostMapping("findAll")
    public List<Package> findAll(@RequestBody Package backpack){
        Example example = new Example(Package.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("growerId",backpack.getId());

        return packageMapper.selectByExample(example);
    }
}
