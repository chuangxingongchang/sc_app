package com.school.condition;

import com.school.util.ShiroMD5;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    private static final transient Logger log = LoggerFactory.getLogger(ShiroRealm.class);
    /**
     * 授权用户权限
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        log.info("from controller request");
        //3.模拟数据库查询
        if (username.equals("username") ) {
            throw new UnknownAccountException( "用户不存在" );
        }
        //从数据库获取的数据
        //principal：认证的实体信息，可以是username；或是数据表对应的实体类对象
        Object object = username;
        // credentials:密码  必须加密不然 参数异常
        Object password = null;
        ShiroMD5 shiroMD5 = new ShiroMD5(username,"123");
        password = shiroMD5.ShiroMD5();
        System.out.println(password);
        //salt 盐值加密 username+slat
        ByteSource salt = new ShiroMD5().saltMD5(username);
        //realmName 当前Realm 对象的 name , getName()
        String realmName = getName();
        //如果不匹配.....会发生shiro的异常 例如不存在 不匹配 ..
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(object, password, salt, realmName);
        return simpleAuthenticationInfo;
    }

    //授权会被shiro 回调
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取登录用户信息 name
        Object object = principalCollection.getPrimaryPrincipal();
        //2.(数据)利用登录的用户信息 授予权限
        Set<String> set = new HashSet<>();
        //如果是admin则可以拥有user 的realm 和 admin的 realm
        set.add("user");
        if ( object.equals("admin") ) {
            set.add("admin");
        }
        //返回
        SimpleAuthorizationInfo simpleAuthorizationInfo =
                new SimpleAuthorizationInfo(set);
        return simpleAuthorizationInfo;

    }
}
