package com.mrone.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @program: shiro
 * @description: 多realm处理
 * @author: Mr.One
 * @create: 2022-06-24 17:06
 **/
@Slf4j
public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();
        //强制转换用户token
        JwtToken jwtToken = (JwtToken) authenticationToken;
        Collection<Realm> realms = getRealms();
        String loginType = jwtToken.getLoginType();
        Collection<Realm> typeRealms = new ArrayList<>();
        Realm realm = null;
        for (Realm realmItem : realms) {
            log.info("Realm>>>>{}", realmItem.getName());
            log.info("Realm>>>>{}", loginType);
            if (realmItem.getName().contains(loginType)) {
                log.info("Realm>>{}", "user请求");
                realm = realmItem;
                break;
            }
            if (realmItem.getName().contains(loginType)) {
                log.info("Realm>>{}", "admin请求");
                realm = realmItem;
                break;
            }
        }
        if (typeRealms.size() == 1) {

            return doSingleRealmAuthentication(typeRealms.iterator().next(), jwtToken);
        } else {

            return doMultiRealmAuthentication((Collection<Realm>) realm, jwtToken);
        }

    }
}
