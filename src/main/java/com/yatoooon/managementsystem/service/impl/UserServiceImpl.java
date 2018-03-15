package com.yatoooon.managementsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yatoooon.managementsystem.model.User;
import com.yatoooon.managementsystem.dao.UserMapper;
import com.yatoooon.managementsystem.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yatoooon.managementsystem.utils.JsonResult;
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
	@Override
	public JsonResult login(String username, String password) {
		if (username == null || password == null || username.equals("") || password.equals("")) {
			return new JsonResult(false,"操作失败",400);
		}
		User user = new User();
		user.setLoginname(username);
		User one = baseMapper.selectOne(user);
		return new JsonResult(one);
	}
}
