package com.cc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.config.Constants;
import com.cc.config.Encryption;
import com.cc.config.Result;
import com.cc.entity.User;
import com.cc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : WZX
 * @create 2022/5/27 11:47
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login/{uname}/{upassword}")
    public Result getUser(@PathVariable String uname, @PathVariable String upassword) {
        System.out.println("1111");
        //解密
        upassword = Encryption.encryption(upassword);
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("uname", uname);
        User user = userService.getOne(qw);
        if(user == null)
            return Result.error(Constants.CODE_400, "账号不存在！");
        if (userService.getOne(qw.eq("upassword", upassword)) == null)
            return Result.error(Constants.CODE_400, "密码错误！");

        return Result.success(user);
    }

    @PostMapping("/edit")
    public Result editUser(@RequestParam int uid, @RequestParam String uname, @RequestParam String upassword, @RequestParam String uemail,
                           @RequestParam String usex, @RequestParam String uphone, @RequestParam String uinfo) {
        //对密码进行加密
        upassword = Encryption.encryption(upassword);
        QueryWrapper<User> qw1 = new QueryWrapper<>();
        QueryWrapper<User> qw2 = new QueryWrapper<>();
        // 查询账号是否已经存在
        qw1.eq("uname", uname);
        int count = (int) userService.count(qw1);
        int reCount = (int) userService.count(qw1.eq("uid", uid));
        System.out.println(count);
        if (count == 1 && reCount == 0) {
            return Result.error(Constants.CODE_400, "用户已存在");
        }
        // 根据id寻找更改的用户信息
        qw2.eq("uid", uid);
        User user = userService.getOne(qw2);
        user.setUname(uname);
        user.setUpassword(upassword);
        user.setUemail(uemail);
        user.setUsex(usex);
        user.setUphone(uphone);
        user.setUinfo(uinfo);
        userService.updateById(user);
        user.setUpassword(Encryption.decryption(upassword));
        return Result.success(user);
    }


    @PostMapping("/register")
    public Result addUser(@RequestParam String uname,@RequestParam String upassword,@RequestParam String uemail){
        QueryWrapper<User> qw = new QueryWrapper<>();
        //查询账号是否重复
        int num = (int) userService.count(qw.eq("uname",uname));
        if(num == 0){
            User user = new User();
            user.setUname(uname);
            user.setUpassword(Encryption.encryption(upassword));
            user.setUemail(uemail);
            userService.save(user);
            return Result.success(user);
        }
        return Result.error(Constants.CODE_400, "用户已存在");
    }
}
