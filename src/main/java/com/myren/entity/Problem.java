package com.myren.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者:HITMyren
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Problem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    private Long sid;

    private String title;

    private String content;

    private String input;

    private String output;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created;

    @TableField("sampleinput")
    private String sampleinput;

    private Integer status;

    private String difficult;

    @TableField("sampleoutput")
    private String sampleoutput;
}
