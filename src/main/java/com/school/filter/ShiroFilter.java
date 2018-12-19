package com.school.filter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShiroFilter {

    public Map<String, String> putFilter(){
        Map<String, String> map = new LinkedHashMap<>();
        /*认证：anon 匿名访问 authc 必须认证即登录 logout 登出 */
        /*授权：roles perms /** = roles[权限admin]*/
        map.put("/login", "anon");
        map.put("/shiro/login", "anon");
        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        map.put("/shiro/logout", "logout");
        map.put("/user","roles[user]");
        map.put("/admin", "roles[admin]");

        //通过记住我登录即可访问
        //map.put("/shiro/home","user");
       // map.put("/html/**", "roles[user,admin]");
        // 从数据库获取动态的权限 用户，需要角色权限 “user”
        //filterChainDefinitionMap.put("/html/**", "roles[user]");
        //管理员，需要角色权限 “admin”
        //filterChainDefinitionMap.put("/html/admin/**", "roles[admin]");
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        map.put("/**", "authc");
        return map;
    }
}
