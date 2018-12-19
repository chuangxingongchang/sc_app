package com.school.config;

import com.school.condition.ShiroRealm;
import com.school.filter.ShiroFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ShiroConfig {
    private static final transient Logger log = LoggerFactory.getLogger(ShiroConfig.class);
    //1.设置filterManager
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //如果和spring集成还是角色Spring处理请求
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
          //shiroFilterFactoryBean.setSuccessUrl("/html/success");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/err");
        //拦截器
        //开放登陆接口
        Map map = new ShiroFilter().putFilter();
        // 从数据库获取
        /*List<SysPermissionInit> list = sysPermissionInitService.selectAll();

        for (SysPermissionInit sysPermissionInit : list) {
            filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
                    sysPermissionInit.getPermissionInit());
        }*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //至少有一个 生效
        securityManager.setAuthenticator(modularRealmAuthenticator());
        // 设置realm.自定义实现Realm管理 实现多Realm
        List<Realm> list = new ArrayList<>();
        //使用凭证匹配器
        list.add(myShiroRealm(hashedCredentialsMatcher()));
        //list.add(myShiroRealm2());
        securityManager.setRealms(list);
        // 自定义缓存实现 使用redis
        //securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        //securityManager.setSessionManager(sessionManager());
        //注入记住我管理器;
        // securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }
    /*Realm实现自定义*/
    public ShiroRealm myShiroRealm(HashedCredentialsMatcher matcher){
        ShiroRealm myShiroRealm = new ShiroRealm();
        myShiroRealm.setCredentialsMatcher(matcher);
        return myShiroRealm;
    }
    /**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 采用MD5方式加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    /*认证策略*/
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        ModularRealmAuthenticator modularRealmAuthenticator=new ModularRealmAuthenticator();
        //shiro注入的时候new...策略
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    /**
     *
     *
     * cookie对象;
     * @return
     */
/*    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }*/

    /**
     * cookie管理对象;记住我功能
     * @return
     */
  /*  public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }*/

}
