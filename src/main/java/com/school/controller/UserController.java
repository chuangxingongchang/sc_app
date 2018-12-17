package com.school.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.mapper.UserMapper;
import com.school.pojo.User;
import com.school.service.UserCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCommonMapper mapper;


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User selectById(@PathVariable long id){
        return userMapper.findById(id);
    }
    @RequestMapping(value = {""},method = RequestMethod.POST)
    public String addUser(User user){
        userMapper.inertUser(user);
        return "现在自增id为"+user.getId();
    }
    /*获取分页数据 包含分页详细信息*/
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public PageInfo getAll(@RequestParam Integer page, @RequestParam Integer size){
        PageHelper.startPage(page,size);
        List<User> users = userMapper.listUser();
        PageInfo<User> pageInfo = new PageInfo(users);
        return pageInfo;
    }

    /*仅获取分页数据
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<User> getAll(@RequestParam Integer page, @RequestParam Integer size){
        PageHelper.startPage(page,size);
        List<User> users = userMapper.listUser();
        return users;
    }*/

    /*整合通用Mapper*/
    @RequestMapping("/testCommonMapperForSelectAll")
    public List<User> testCommonMapper(){
        return mapper.selectAll();
    }
}
