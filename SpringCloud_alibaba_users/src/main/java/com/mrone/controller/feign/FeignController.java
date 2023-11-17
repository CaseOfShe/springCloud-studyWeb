package com.mrone.controller.feign;

import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.dto.UserDto;
import com.mrone.service.UserNumService;
import com.mrone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: studyweb
 * @description:
 * @author: Mr.One
 * @create: 2022-09-08 18:12
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserNumService userNumService;
    //admin
    @PostMapping("/byopenid")
    public UserDto byOpenid(String openid){
        UserDto byOpenid = userService.findOne();
        return byOpenid;
    }

    @PostMapping("/byall")
    public List<UserDto> byAll(){
        List<UserDto> byAll = userService.findAll();
        return byAll;
    }

    @PostMapping("/bynumid")
    public Result<Object> byNumId(int id){
        String openidById = userNumService.findOpenidById(id);
        if(openidById==null){
            return null;
        }
        Result<Object> result = userNumService.delById(id);
        userService.delByOpenid(openidById);
        return result;
    }

    //article
    @GetMapping("/user")
    public UserDto indexUserInfo(){
        UserDto one = userService.findOne();
        return one;
    }

    @GetMapping("/user/id")
    public Result<Object> findUserById(Integer id){
        Result<Object> byId = userService.findById(id);
        return byId;
    }


}
