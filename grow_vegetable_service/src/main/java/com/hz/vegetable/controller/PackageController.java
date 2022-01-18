package com.hz.vegetable.controller;

import com.hz.domain.Package;
import com.hz.domain.complex.PackageObj;
import com.hz.mapper.PackageMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

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
    PackageMapper packageMapper;

    @PostMapping("findAll")
    public List<PackageObj> findAll(@RequestBody Package backpack){
        Example example = new Example(Package.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("growerId",backpack.getId());

        return packageMapper.selectAllByUserId(backpack.getGrowerId());
    }
}
