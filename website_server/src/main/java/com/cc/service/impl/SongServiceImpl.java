package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.entity.Song;
import com.cc.service.SongService;
import com.cc.mapper.SongMapper;
import org.springframework.stereotype.Service;

/**
* @author 86134
* @description 针对表【song】的数据库操作Service实现
* @createDate 2022-06-02 00:20:10
*/
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song>
    implements SongService{

}




