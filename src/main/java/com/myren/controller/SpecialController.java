package com.myren.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myren.common.lang.Result;
import com.myren.entity.Blog;
import com.myren.entity.Special;
import com.myren.mapper.SpecialMapper;
import com.myren.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者:HITMyren
 * @since 2021-05-21
 */
@RestController
public class SpecialController {
    @Autowired
    SpecialMapper specialMapper;
    @Autowired
    SpecialService specialService;
    @GetMapping("/specials")
    public Result specials(){
        List<Special>specials = specialService.list();
        return Result.success(specials);
    }

}
