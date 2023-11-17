package com.mrone.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created 一字先生
 *
 * @Author: Mr.One
 * @Date: 2022/06/14 14:14
 * @Description: 重写shiro token
 */
public class JwtToken implements AuthenticationToken{

    private String loginType;
    private final  String  token;

    public  JwtToken(String token){
        this.token=token;
    }

    // 增加
    public JwtToken(String token, String loginType) {
        this.token = token;
        this.loginType = loginType;
    }


    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

}
