package com.mrone.controller;

import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.service.UserService;
import com.mrone.util.JwtUtil;
import com.mrone.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;


@Controller
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;



    /**
     * 主页
     */
    @GetMapping({"/","/index"})
    public String index(){
        return "你又来看我辣";
    }



    /**
     * 登录验证
     */
    @PostMapping("/login")
    public Result<Object> login(String code) {
        Result<Object> result = new Result<>();
        Object openId = redisUtil.get("openid");
        // 1、校验content的合法性
        if (code.length() != 6 || redisUtil.get("code" + code) == null) {
            result.setMessage(ResultCode.AUTH_CODE_ERROR.message())
                    .setCode(ResultCode.AUTH_CODE_ERROR.code());
            return result;
        } else {
            //忽略大小写进行验证，并且判断是否成功登录
            log.info("{}用户登录",openId);
            if(String.valueOf(redisUtil.get("code"+code)).equals(code)) {
                Result<Object> SuccessResult = userService.registerAndLogin(String.valueOf(openId));
                result = SuccessResult;
                return result;
            }
        }
        return result;
    }


    /**
     *信息修改
     */
    @PostMapping("/update/username")
    public Result<Object> updateUserName(Integer id,String username){
        Result<Object> result = userService.updateUserInfo(id, username);
        return result;
    }

    /**
     * 头像
     */
    @PostMapping("/update/avatar")
    public Result<Object> updateAvatar(Integer id,String avatar){
        Result<Object> result = userService.updateAvatar(id,avatar);
        return result;
    }



    /**
     * 退出登录
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



}
