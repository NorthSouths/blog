package com.myren.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code; //200正常 非200表示异常数据
    private String msg;
    private Object data;

    public static Result success(String 提交成功,Object data){
        return  success(200,"操作成功",data);
    }

    public static Result success(int code,String msg,Object data){
        Result result=new Result();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return  result;
    }

    public static Result fail(String msg){
        return  fail(400,msg,null);
    }

    public static Result fail(String msg,Object data){
        return  fail(400,msg,data);
    }

    public static Result fail(int code,String msg,Object data){
        Result result=new Result();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return  result;
    }

}
