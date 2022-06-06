package com.myren.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myren.common.lang.Result;
import com.myren.entity.Problem;
import com.myren.mapper.ProblemMapper;
import com.myren.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
public class ProblemController {
    @Autowired
    ProblemService problemService;
    @Autowired
    ProblemMapper problemMapper;
    @GetMapping("/special/{sid}")
    public Result special(@PathVariable(name = "sid")Long sid){
        QueryWrapper<Problem> wrapper=new QueryWrapper<>();
        wrapper.eq("sid",sid);
        List<Problem> problems = problemMapper.selectList(wrapper);
        if(problems!=null)
        return Result.success("提交成功", problems);
        else
            throw new AssertionError("找不到该专题");
    }
    @GetMapping("/problem/{pid}")
    public Result problem(@PathVariable(name = "pid") Long pid){
        Problem problem = problemMapper.selectById(pid);
        return Result.success("提交成功", problem);
    }
    @PostMapping("/make")
    public Result makeProblem(@Validated @RequestBody Problem problem){
        problem.setStatus(0);
        problem.setCreated(LocalDateTime.now());

        problemService.save(problem);
        return Result.success("提交成功", "插入成功");
    }
}
