package com.myren.entity;

import java.io.Serializable;
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
public class Overp implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long pid;

    private Integer status;

    private String content;

    private String title;


}
