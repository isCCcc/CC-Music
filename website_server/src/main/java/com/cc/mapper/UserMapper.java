package com.cc.mapper;

import com.cc.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86134
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-05-27 16:42:47
* @Entity com.cc.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




