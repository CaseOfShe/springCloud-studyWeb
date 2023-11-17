package com.mrone.config;

import com.mrone.dto.UserDto;
import com.mrone.entity.User;
import com.mrone.feignclients.UserClient;
import com.mrone.service.UserService;
import com.mrone.util.JwtUtil;
import com.mrone.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private JwtUtil jwtConfig;

    @Autowired
    private RedisUtil redisUtill;

    @Autowired
    private UserService userService;


    @Override
    public String getName() {
        return "user";
    }

    /**
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     */

    @Override
    public boolean supports(AuthenticationToken token) {
        // 这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String openid = (String) principalCollection.getPrimaryPrincipal();
        if(openid==null){
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.findByOpenid(openid);

        if(user==null){
            return null;
        }
        if(user.getRole()==null&&user.getPermission()==null||user.getRole().equals("")&&user.getPermission().equals("")){
            //默认user用户权限
            authorizationInfo.addRole("user");
            authorizationInfo.addStringPermission("user");
        }else {
            //数据库获取角色权限 授权
            authorizationInfo.addRole(user.getRole());
            authorizationInfo.addStringPermission(user.getPermission());
        }

        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("进行用户登录认证");

        String token = (String) authenticationToken.getPrincipal();
        //解码token
        String openid = jwtConfig.decodeToken(token);
        if (openid == null || token == null) {
            throw new IncorrectCredentialsException("Authorization token is invalid");//抛出异常 没有token
        }
        //与redis里的数据进行比较 看是否相等
        Object user = redisUtill.get(openid);
        if (user != null) {
            // claims放入全局Subject中
            return new SimpleAuthenticationInfo(openid, token, "UserRealm");
        }
        return null;

    }
}
