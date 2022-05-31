package com.myren.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myren.common.dto.LoginDto;
import com.myren.common.lang.Result;
import com.myren.entity.User;
import com.myren.service.UserService;
import com.myren.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api
public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    //登录
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @ApiImplicitParam(paramType="header",name="token",dataType="String",value="token",defaultValue="")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){

        User user=userService.getOne(new QueryWrapper<User>().eq("username",loginDto.getUsername()));

        if(user==null){
            throw new IllegalArgumentException("该用户不存在");
        }
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("用户名或密码不正确");
        }

        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return  Result.success(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("avatar",user.getAvatar())
                .put("email",user.getEmail())
                .put("status",user.getStatus())
                .map()
        );
    }

    //登出
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
    //注册
    @GetMapping("/register")
    public Result register(User user){

        return Result.success(null);
    }
}
