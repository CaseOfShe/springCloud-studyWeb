package com.mrone.controller;

import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.entity.Admin;
import com.mrone.service.AdminService;
import com.mrone.util.JwtUtil;
import com.mrone.util.RedisUtil;
import com.mrone.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created 一字先生
 *
 * @Author: Mr.One
 * @Date: 2022/06/24 10:40
 * @Description:
 */
@Slf4j
@Controller
@ResponseBody
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 管理员登录
     * @param phone
     * @param password
     * @return
     */

    @PostMapping("/login")
    public Result<Object> adminLogin(String phone,String password){
        Result<Object> result = new Result<>();
        Object o = redisUtil.get(phone);
        if (null!=o){
            result.setCode(ResultCode.USER_NOT_LOGIN_TWICE.code())
                    .setMessage(ResultCode.USER_NOT_LOGIN_TWICE.message());
        }
        Admin Phone = adminService.findByPhone2(phone);
        if (Phone==null){
            result.setCode(ResultCode.USER_LOGIN_ERROR
            .code()).setMessage(ResultCode.USER_LOGIN_ERROR.message());
            return result;
        }
        String s = ShiroUtil.md5(password);
        boolean b = ShiroUtil.verifyPassword(password, Phone.getPassword());
        if(!b){
            result.setCode(ResultCode.USER_LOGIN_ERROR.code())
                    .setMessage(ResultCode.USER_LOGIN_ERROR.message());
            return result;
        }
        //有 登录 并存入redis缓存
        redisUtil.set(phone,phone);
        log.info("{}管理员登录",phone);
        String token = jwtUtil.createToken2(phone);
        result.setCode(ResultCode.USER_SUCCESS_LOGIN.code())
                .setMessage(ResultCode.USER_SUCCESS_LOGIN.message())
                .setData(token);
        return result;
    }


    /**
     * 添加管理员
     * @param phone
     * @param name
     * @param password
     * @param role
     * @param permission
     * @return
     */
    @RequiresAuthentication
    @RequiresRoles("root")
    @RequiresPermissions("root")
    @PostMapping("/add")
    public Result<Object> addAdmin(String phone, String name, String password, String role, String permission){
        String s = ShiroUtil.md5(password);
        Result<Object> result = adminService.addAdmin(phone, name, s, role, permission);
        return result;
    }




    /**
     * 管理员退出
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/logout")
    public Result<String> logout() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        log.info("{}退出",principals);
        //获取全局用户名 并将redis里存储的用户删除
        redisUtil.del(String.valueOf(principals));
        SecurityUtils.getSubject().logout();
        Result<String> result = new Result<>();
        result.setMessage(ResultCode.USER_SUCCESS_LOGOUT.message());
        return result;
    }

    /**
     * 忘记密码重置
     * @param phone
     * @param name
     * @return
     */
    @PostMapping("/forget")
    public Result<Object> forget(String phone, String name, String password){
        Result<Object> result = new Result<>();
        return result;
    }
}
