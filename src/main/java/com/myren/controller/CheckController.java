package com.myren.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myren.common.lang.Result;
import com.myren.entity.Best;
import com.myren.entity.MidMoudle;
import com.myren.entity.Overp;
import com.myren.entity.Rank;
import com.myren.mapper.OverpMapper;
import com.myren.mapper.ProblemMapper;
import com.myren.service.OverpService;
import com.myren.submit.HDUSubmitter;
import com.myren.submit.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckController {

    @Autowired
    @Qualifier("hduSubmitter1")
    HDUSubmitter hduSubmitter1;
    @Autowired
    OverpService overpService;
    @Autowired
    OverpMapper overMapper;
    @Autowired
    ProblemMapper problemMapper;
    @PostMapping("/check")
    public Result check(@RequestBody Overp over){
        QueryWrapper<Overp>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",over.getId());
        queryWrapper.eq("pid",over.getPid());
        Overp overp = overMapper.selectOne(queryWrapper);
        if(overp==null||overp.getStatus()==-1){
            overpService.save(over);
            //提交至hdu判断
            Submission submission = new Submission();
            submission.setLanguage(0).setOriginProblemId(1000+over.getId().toString()).setSourceCode(over.getContent());
            hduSubmitter1.setSubmission(submission);
            hduSubmitter1.work();
            com.myren.submit.Result result = hduSubmitter1.getResult();
            System.out.println(result);
            //
            return Result.success("提交成功",result);
        }
        else{
            if(overp.getStatus()==0)
                throw new IllegalArgumentException("您已经提交过了,等待审批中");
            else
                throw new IllegalArgumentException("此题您已经正确");
        }
    }

    @RequestMapping(value = "/checks",method = RequestMethod.GET)
    public Result checks(@RequestParam(defaultValue = "1") Integer currentPage){
        QueryWrapper<Overp>queryWrapper = new QueryWrapper<>();
        //嵌套查询
        queryWrapper.eq("status",0);
        List<Overp> list  = overMapper.selectList(queryWrapper);
/*        if(list==null){
            return Result.success("暂时没人提交！");
        }*/
        Page page = new Page(currentPage,5);
        IPage pageData = overpService.page(page, queryWrapper);
        return Result.success(pageData);
    }
    @PostMapping("/right")
    public Result right(@RequestBody Overp overp){
        overp.setStatus(1);
        overMapper.UpdateByIdandPid(overp.getId(), overp.getPid());
        problemMapper.checkSuccess(overp.getPid());
        return Result.success("成功");
    }
    @PostMapping("/failure")
    public Result failure(@RequestBody Overp overp){
        overp.setStatus(-1);
        overMapper.UpdateByIdandPidtwo(overp.getId(), overp.getPid());
        return Result.success("成功");
    }
    @GetMapping("/ranksu/{id}")
    public Result ranksucesss(@PathVariable(name = "id") Long id){
/*        queryWrapper.eq("status",1);*/
/*        QueryWrapper<Overp> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id",id);
        queryWrapper1.eq("status",-1);*/
        List<MidMoudle>list =overMapper.rank(id);
/*        List<Overp>list1 =overMapper.selectList(queryWrapper1);
        list.addAll(list1);*/
        return Result.success(list);
    }
    @GetMapping("/rank/{id}")
    public Result rank(@PathVariable Long id){
        Rank rank = new Rank();
        rank.setAC(overMapper.countAC(id));
        rank.setWA(overMapper.countWA(id));
        rank.setTR(overMapper.countTR(id));
        return Result.success(rank);
    }
    @GetMapping("/best")
    public Result best(){
        List<Best> bests=overMapper.best();
        return Result.success(bests);
    }
}
