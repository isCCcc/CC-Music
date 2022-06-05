package com.cc.mapper;

import com.cc.entity.Singer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86134
* @description 针对表【singer】的数据库操作Mapper
* @createDate 2022-05-29 21:53:51
* @Entity com.cc.entity.Singer
*/
@Mapper
public interface SingerMapper extends BaseMapper<Singer> {

}




