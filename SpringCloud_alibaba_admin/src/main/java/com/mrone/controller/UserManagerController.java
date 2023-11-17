package com.mrone.controller;

import com.mrone.commons.Result;
import com.mrone.dto.UserDto;
import com.mrone.service.UserNumService;
import com.mrone.service.UserService;
import com.mrone.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: shiro
 * @description: 用户管理
 * @author: Mr.One
 * @create: 2022-07-26 11:49
 **/
//@Slf4j
@ResponseBody
@Controller
@RequestMapping("/admin")
public class UserManagerController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserNumService userNumService;


    @Autowired
    private RedisUtil redisUtil;



    @PostMapping("/user/show")
    public List<UserDto> show(){
        List<UserDto> users = userService.findAll();
        return users;
    }


    /**
     * 根据第几位注册用户删除
     * @param id
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles(logical = Logical.OR, value = {"admin", "root"})
    @RequiresPermissions(value = {"admin","root"},logical = Logical.OR)
    @PostMapping("/user/del")
    public Result<Object> UserPower(int id){
        System.out.println(id);
        Result<Object> result = userNumService.delById(id);
        return result;
    }

//    @PostMapping("/user/info")
//    public Result<Object> userInfo(String openid,String avatar,String name){
//        redisUtil.get("openid");
//    }
    
}
