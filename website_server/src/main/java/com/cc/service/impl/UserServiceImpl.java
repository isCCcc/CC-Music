package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.entity.User;
import com.cc.service.UserService;
import com.cc.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 86134
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-05-27 16:42:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




