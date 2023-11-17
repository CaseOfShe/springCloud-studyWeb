package com.mrone.config;

import com.mrone.filter.ShiroFilter;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    /**
     * 告诉shiro不创建内置的session
     */
    @Bean
    public SubjectFactory subjectFactory() {
        return new ShiroDefaultSubjectFactory();
    }

    //shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterBean(@Qualifier("Manager") DefaultSecurityManager defaultSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultSecurityManager);

        /**
         * 添加内置shiro过滤器
         * anno 无需认证可访问
         * authc认证了才能访问
         * user 必须拥有记住我功能 才能使用
         * perms 拥有对某个资源的权限才能访问
         * role 拥有某个角色权限才能访问
         */
        // 注册jwt过滤器,也就是将jwtFilter注册到shiro的Filter中，并在下面注册,指定除了login和index之外的请求都先经过jwtFilter

        Map<String, Filter> filterMap = new HashMap<String, Filter>(3) {
            {
                put("anon", new AnonymousFilter());
                put("jwt", new ShiroFilter());
                put("logout", new LogoutFilter());
            }
        };
        bean.setFilters(filterMap);
        //设置拦截器
        Map<String, String> filterRuleMap = new LinkedHashMap<String, String>() {
            {
                //登录注册主页放行
                put("/login", "anon");
                put("/register", "anon");
                put("/","anon");
                put("/index","anon");
                put("/wx/**","anon");
                put("/user/login","anon");
                put("/admin/login","anon");
                // swagger放行
                put("/swagger-ui/index.html", "anon");
                put("/swagger-resources", "anon");
                put("/v2/api-docs", "anon");
                put("/webjars/springfox-swagger-ui/**", "anon");
                put("/configuration/security", "anon");
                put("/configuration/ui", "anon");
                put("/feign/**","anon");
                // 任何请求都需要经过jwt过滤器
                put("/**", "jwt");
            }
        };
        bean.setFilterChainDefinitionMap(filterRuleMap);
        return bean;
    }



    /**
     * 创建安全管理器
     */
    @Bean("Manager")
    public DefaultWebSecurityManager getManager(UserRealm userRealm, AdminRealm adminRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        List<Realm> list = new ArrayList<>();
        // 使用自己的realm
        list.add(userRealm);
        list.add(adminRealm);
        manager.setRealms(list);
        // 关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }


    /**
     * 系统自带的Realm管理，主要针对多realm
     * */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());//这里为默认策略：如果有一个或多个Realm验证成功，所有的尝试都被认为是成功的，如果没有一个验证成功，则该次尝试失败
        return modularRealmAuthenticator;
    }


    //开启Shiro注解支持
    /**
     * 如果userPrefix和proxyTargetClass都为false会导致 aop和shiro权限注解不兼容 资源报错404
     * 因此两个属性至少需要其中一个属性为true才可以
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /**
     * 开启aop注解支持
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("Manager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager); // 这里需要注入 SecurityManger 安全管理器
        return authorizationAttributeSourceAdvisor;
    }
}

