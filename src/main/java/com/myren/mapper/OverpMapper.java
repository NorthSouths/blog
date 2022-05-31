package com.myren.mapper;

import com.myren.entity.Best;
import com.myren.entity.MidMoudle;
import com.myren.entity.Overp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者:HITMyren
 * @since 2021-05-22
 */
@Repository
public interface OverpMapper extends BaseMapper<Overp> {
    int UpdateByIdandPid(Long id,Long pid);
    int UpdateByIdandPidtwo(Long id,Long pid);
    List<MidMoudle> rank(Long id);
    int countAC(Long id);
    int countWA(Long id);
    int countTR(Long id);
    List<Best> best();
}
