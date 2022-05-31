package com.myren.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.PipedReader;
import java.io.Serializable;

@Data
public class Best implements Serializable {

    private String username;

    private Integer ac;
}
