package com.cc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.config.Result;
import com.cc.entity.Singer;
import com.cc.entity.Song;
import com.cc.service.SingerService;
import com.cc.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : WZX
 * @create 2022/6/1 15:49
 */
@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    SingerService singerService;
    @Autowired
    SongService songService;

    @GetMapping("/")
    public Result getAllSinger(){
        QueryWrapper<Singer> qw = new QueryWrapper<>();
        List<Singer> list = singerService.list(qw);
        return Result.success(list);
    }

    @PostMapping("/singerAllSongs")
    public Result getSinger(@RequestParam int id){
        QueryWrapper<Song> qw = new QueryWrapper<>();
        qw.eq("singer_id",id);
        List<Song> list = songService.list(qw);
        return Result.success(list);
        // System.out.println(list);
        // return null;
    }
}
