package com.cc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.entity.Collection;
import com.cc.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @GetMapping("/displayall")
    public List<Collection> getCollection(){
        QueryWrapper<Collection> qw = new QueryWrapper<>();
        List<Collection> list = collectionService.list(qw);
        // System.out.println(list);
        return list;
    }

    @RequestMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable int id){
        System.out.println(id);
        QueryWrapper<Collection> qw = new QueryWrapper<>();
        Boolean isDelete = collectionService.removeById(id);
        System.out.println(isDelete);
        return isDelete;
    }
}
