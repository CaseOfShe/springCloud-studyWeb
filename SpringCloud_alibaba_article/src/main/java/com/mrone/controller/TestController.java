package com.mrone.controller;

import com.mrone.commons.Result;
import com.mrone.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: shiro
 * @description: 测试Token
 * @author: Mr.One
 * @create: 2022-06-14 19:29
 *
 * @RequiresAuthentication 需要token才可以访问
 * @RequiresRoles 需要角色权限才可以访问
 * @RequiresPermissions 拥有权限才可以访问
 **/
@ResponseBody
@Controller
public class TestController {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    @RequiresAuthentication
//    @RequiresRoles(logical = Logical.OR,value = {"admin","vip","user"})
//    @PostMapping("/test")
//    public String test(){
//        String openid =String.valueOf(SecurityUtils.getSubject().getPrincipals());
//        if(redisUtil.get(openid)==null){
//            return "开通失败";
//        }
//            String role = "vip";
//            String permission = "vip";
//            userService.updateRoleAndPermission(role,permission);
//            return "恭喜你开通会员";
//    }


    // 拥有 user 或 vip 角色，且拥有 vip 权限可以访问
    @RequiresAuthentication
    @PostMapping("/getvip")
    @RequiresRoles(logical = Logical.OR, value = {"admin", "vip"})
    @RequiresPermissions(value = {"admin","vip"},logical = Logical.OR)
    public Result<Object> vip(){
        Result<Object> result = new Result<>();
        result.setCode(200).setMessage("获取到了VIP");
        return result;
    }

    @RequiresAuthentication
    @RequiresRoles("admin")
    @RequiresPermissions("admin")
    @PostMapping("/admin")
    public Result<Object> admin(){
        Result<Object> result = new Result<>();
        result.setCode(200).setMessage("获取到了VIP");
        return result;
    }


}
