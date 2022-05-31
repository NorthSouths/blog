package com.myren.mapper;

import com.myren.entity.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者:HITMyren
 * @since 2021-05-22
 */
@Repository
public interface ProblemMapper extends BaseMapper<Problem> {
    int checkSuccess(Long pid);
}
