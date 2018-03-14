package com.yatoooon.managementsystem.service.impl;

import com.yatoooon.managementsystem.model.User;
import com.yatoooon.managementsystem.dao.UserMapper;
import com.yatoooon.managementsystem.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yatoooon
 * @since 2018-03-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
