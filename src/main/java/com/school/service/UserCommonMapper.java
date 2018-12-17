package com.school.service;

import com.school.pojo.User;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.BaseMapper;
@Service
public interface UserCommonMapper extends BaseMapper<User> {
}
