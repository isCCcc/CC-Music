package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.entity.Singer;
import com.cc.service.SingerService;
import com.cc.mapper.SingerMapper;
import org.springframework.stereotype.Service;

/**
* @author 86134
* @description 针对表【singer】的数据库操作Service实现
* @createDate 2022-05-29 21:53:51
*/
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer>
    implements SingerService{

}




