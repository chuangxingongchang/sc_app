package com.school.example;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExampleTestMapper {

    /*注解方式的话直接在方法上写上对应的sql语句就可以了*/
    @Select("select * from user where id = #{id}")
    /*如果需要重复使用该Result,给该Results加一个 id 属性，下面即可使用@ResultMap(id)重复调用*/
    @Results(id = "user",value = {
            @Result(property = "username",column = "name")
    })
    Object findById(long id);

    /*获取回传自增id*/
    /*id会自动存入user中*/
    @Insert("insert into user(name,passwd) values (#{username},#{passwd})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int inertUser(Object user);

    @Select("select * from user")
    /*调用之前的Results*/
    @ResultMap("user")
    List<Object> listUser();
}
