package com.hz.vegetable.controller;

import com.hz.domain.TestUser;
import com.hz.mapper.TestUserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author HuangZ
 * @version v1.0
 * @date 2022/1/14
 **/
@RestController
@RequestMapping("test")
public class TestController {
    @Resource
    TestUserMapper testUserMapper;

    @PostMapping("getTestUser")
    public String getTestUser(){
        TestUser testUser = new TestUser();
        testUser.setName("Tom");
        testUserMapper.insert(testUser);
        List<TestUser> testUsers = new LinkedList<>();
        IntStream.range(0,10).forEach(index -> {
            TestUser user = new TestUser();
            user.setName("Tom" + index);
            testUsers.add(user);
        });
        testUserMapper.insertList(testUsers);
        return "success";
    }
}
