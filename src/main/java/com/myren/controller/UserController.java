package com.myren.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myren.common.dto.UserDto;
import com.myren.common.lang.Result;
import com.myren.entity.User;
import com.myren.mapper.UserMapper;
import com.myren.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者:HITMyren
 * @since 2021-05-12
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    //获得谁写的博客
    @GetMapping("/user/{id}")
    public Result user(@PathVariable(name = "id") Long id){
        User user = userService.getById(id);
        Assert.notNull(user,"该用户不存在");
        user.setPassword(null);
        return Result.success("提交成功", user);
    }
    @PostMapping("/user/register")
    public Result register(@Validated @RequestBody UserDto user){
        User temp = null;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        if(userMapper.selectOne(wrapper)!=null) {
            throw new AssertionError("该用户已存在");
        }else{
            temp = new User();
            BeanUtil.copyProperties(user,temp);
            temp.setPassword(SecureUtil.md5(user.getPassword()));
            temp.setCreated(LocalDateTime.now());
            int a = 0;
            if(user.getState())
                a=2;
            temp.setStatus(a);
            System.out.println(temp);
            userService.saveOrUpdate(temp);
            return Result.success("提交成功", null);
        }
    }

}