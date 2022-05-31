package com.myren.service.impl;

import com.myren.entity.Blog;
import com.myren.mapper.BlogMapper;
import com.myren.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者:HITMyren
 * @since 2021-05-12
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
