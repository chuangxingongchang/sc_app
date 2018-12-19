package com.school.example;

import com.school.util.ShiroMD5;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
@RestController
@RequestMapping("/shiro")
public class ExampleShiroController {

    private static final transient Logger log = LoggerFactory.getLogger(ExampleShiroController.class);
    //nickname paw 记住我true
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView loginToIndex(@Param("username") String username, @Param("password") String password){
        Subject currentUser = SecurityUtils.getSubject();
        ModelAndView modelAndView = new ModelAndView();
        // 测试当前的用户是否已经被认证. 即是否已经登录.
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                // 执行登录.
                currentUser.login(token);
            }
            // 所有认证时异常的父类.
            catch (AuthenticationException ae) {
                modelAndView.setViewName("redirect:/html/err");
                System.out.println("登录失败");
                return modelAndView;
            }


            //test a role:
            if (currentUser.hasRole("schwartz")) {

            }

            // 测试用户是否具备某一个行为. 调用 Subject 的 isPermitted() 方法。
            if (currentUser.isPermitted("lightsaber:weild")) {
            }
            // 测试用户是否具备某一个行为.
            if (currentUser.isPermitted("user:delete:zhangsan")) {
            }
        }

        modelAndView.setViewName("html/home");
        return modelAndView;
    }

    @RequestMapping("/insert")
    public ModelAndView insertUser ( String username, String password ) {
        System.out.println("insert");
        ShiroMD5 shiroMD5 = new ShiroMD5();
        String pswd = String.valueOf(new ShiroMD5(username, password));
        //然后存入数据库
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping ( value = "logout" ,method = RequestMethod.POST)
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "redirect:login" );
        SecurityUtils.getSubject().logout();
        return modelAndView;
    }
}
