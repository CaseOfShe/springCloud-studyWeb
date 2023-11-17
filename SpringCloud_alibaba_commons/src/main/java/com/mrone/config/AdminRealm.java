package com.mrone.config;


import com.mrone.dto.AdminDto;
import com.mrone.feignclients.AdminClient;
import com.mrone.service.AdminService;
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

/**
 * Created 一字先生
 *
 * @Author: Mr.One
 * @Date: 2022/06/24 10:46
 * @Description:
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminRealm extends AuthorizingRealm {
    @Autowired
    private JwtUtil jwtConfig;

    @Autowired
    private RedisUtil redisUtill;

    @Autowired
    private AdminService adminService;


    @Override
    public String getName() {
        return "admin";
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
        String phone = (String) principalCollection.getPrimaryPrincipal();
        if(phone==null){
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        AdminDto admin = adminService.findByPhone(phone);
        if(admin==null){
            return null;
        }
        if(admin.getRole()==null&&admin.getPermission()==null||admin.getRole().equals("")&&admin.getPermission().equals("")){
            //默认admin用户权限
            authorizationInfo.addRole("admin");
            authorizationInfo.addStringPermission("admin");
        }else {
            //数据库获取角色权限 授权
            authorizationInfo.addRole(admin.getRole());
            authorizationInfo.addStringPermission(admin.getPermission());
        }

        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("进行管理员用户认证");

        String token = (String) authenticationToken.getPrincipal();
        //解码token admin就是传入的phone
        String phone = jwtConfig.decodeToken2(token);
        if (phone == null || token == null) {
            throw new IncorrectCredentialsException("Authorization token is invalid");//抛出异常 没有token
        }
        //与redis里的数据进行比较 看是否相等
        Object user = redisUtill.get(phone);
        if (user != null) {
            // claims放入全局Subject中
            return new SimpleAuthenticationInfo(phone, token, "AdminRealm");
        }
        return null;

    }




}
