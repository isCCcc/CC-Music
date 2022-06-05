package com.cc.mapper;

import com.cc.entity.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86134
* @description 针对表【song】的数据库操作Mapper
* @createDate 2022-06-02 00:20:09
* @Entity com.cc.entity.Song
*/
@Mapper
public interface SongMapper extends BaseMapper<Song> {

}




