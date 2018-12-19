package com.school.condition;

import com.ssm.boot.util.ShiroMD5;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm2 extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.转为usernamepassowr
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2.获取username
        String username = usernamePasswordToken.getUsername();
        //3.查询数据库 从数据库查询当前用户的信息
        System.out.println("查询到当前用户的信息：" + username);
        //4.若 用户不存在
        if ("abc".equals(username)) {
            //5.根据用户情况抛出对应异常
            throw new UnknownAccountException("用户不存在");
        }
        //6.根据用户情况，来构建AutenticationInfo 对象返回。通常使用实现类 SimpleAuthenticationInfo
        //以下是从数据库获取的数据
        //principal：认证的实体信息，可以是username；或是数据表对应的实体类对象
        Object object = username;
        //credentials:密码
        Object password = "111";
        //salt 盐值加密
        ByteSource salt = new ShiroMD5().saltMD5(username);
        //realmName 当前Realm 对象的 name , getName()
        String realmName = getName();
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(object, password, salt, realmName);
        return simpleAuthenticationInfo;

    }
}
