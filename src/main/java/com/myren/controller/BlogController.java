package com.myren.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myren.common.lang.Result;
import com.myren.entity.Blog;
import com.myren.mapper.BlogMapper;
import com.myren.service.BlogService;
import com.myren.util.ShiroUtil;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,3);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success("提交成功", pageData);
    }
    @GetMapping("/blog/{id}")
    public Result detial(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已被删除");
        return Result.success("提交成功", blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog){
        Blog temp = null;
        if(blog.getId()!=null){
            temp = blogService.getById(blog.getId());
            //只能编辑自己的文章
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()),"没有操作权限");
        }else{
            temp=new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);
        return Result.success("提交成功", null);
    }



    //-----------------------------------
    //等待添加：
    @RequiresAuthentication
    @PostMapping("/blog/del")
    public Result del(@Validated @RequestBody Blog blog){
        Blog temp=null;
        if(blog.getId()!=null){
            temp = blogService.getById(blog.getId());
            //只能删除自己的文章
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()),"没有权限删除文章");
        }
        blogService.removeById(temp.getId().longValue());
        return Result.success("提交成功", "删除成功");
    }
/*    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(pageData);
    }*/
    @RequiresAuthentication
    @GetMapping("/myblog")
    public Result myblog(@RequestParam(defaultValue = "1") Integer currentPage,
                         @RequestParam Integer id){
        QueryWrapper<Blog>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id.longValue());
        Page page = new Page(currentPage,5);
        IPage pageData = blogService.page(page,queryWrapper);
        return Result.success("提交成功", pageData);
    }
}
