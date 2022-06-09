package com.cc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.config.Constants;
import com.cc.config.Result;
import com.cc.entity.Collection;
import com.cc.entity.Singer;
import com.cc.entity.Song;
import com.cc.service.CollectionService;
import com.cc.service.SingerService;
import com.cc.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : WZX
 * @create 2022/5/29 15:09
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    SongService songService;
    @Autowired
    SingerService singerService;
    @Autowired
    CollectionService collectionService;

    //展示所有歌曲
    @GetMapping("/all")
    public Result getAllSong(){
        QueryWrapper<Song> qw = new QueryWrapper<>();
        List<Song> list = songService.list(qw);
        // System.out.println(list);
        return Result.success(list);
    }

    // 添加歌曲到收藏
    @PostMapping("/addToCollection")
    public Result addToCollection(@RequestParam int uid,@RequestParam String sname,
                                  @RequestParam int ssinger,@RequestParam String salbum,@RequestParam String surl){
        QueryWrapper<Singer> singer = new QueryWrapper<>();
        Singer s = singerService.getOne(singer.eq("id", ssinger));

        Collection collection = new Collection();
        collection.setUserId(uid);
        collection.setSongName(sname);
        collection.setSongSinger(s.getName());
        collection.setSongAlbum(salbum);
        collection.setSongUrl(surl);
        collectionService.save(collection);
        return Result.success(collection);
    }

    // 删除收藏歌曲
    @PostMapping("/deleteFromCollection")
    public Result deleteFromCollection(@RequestParam String sname){
        System.out.println(sname);
        QueryWrapper<Collection> qw = new QueryWrapper<>();
        boolean isRemove = collectionService.remove(qw.eq("song_name", sname));
        System.out.println(isRemove);
        if(isRemove){
            return Result.success();
        }
        return Result.error(Constants.CODE_400,"移除失败");
    }
}
