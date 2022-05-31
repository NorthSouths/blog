package com.myren.service.impl;

import com.myren.entity.User;
import com.myren.mapper.UserMapper;
import com.myren.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
