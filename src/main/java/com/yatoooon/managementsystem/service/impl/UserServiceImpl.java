package com.yatoooon.managementsystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yatoooon.managementsystem.model.Role;
import com.yatoooon.managementsystem.model.User;
import com.yatoooon.managementsystem.dao.UserMapper;
import com.yatoooon.managementsystem.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yatoooon.managementsystem.utils.JsonResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yatoooon
 * @since 2018-03-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	@Override
	public JsonResult login(String username, String password, HttpSession session) {
		Map<String, Object> rtnMap = new HashMap<>(1);
		if (username == null || password == null || "".equals(username) || "".equals(password)) {
			return new JsonResult(JsonResult.CODE_ERROR);
		}
		User user = new User();
		user.setLoginname(username);
		user.setPassword(password);
		User one = baseMapper.selectOne(user);
		if (one == null) {
			return new JsonResult(JsonResult.CODE_ERROR, "用户名或密码错误", one);
		}
		String sessionId = session.getId();
		rtnMap.put("token", sessionId);
		one.setToken(sessionId);
		Integer integer = baseMapper.updateById(one);
		if (integer != 1) {
			return new JsonResult(JsonResult.CODE_ERROR);
		}
		return new JsonResult(JsonResult.CODE_SUCCESS, "登录成功", rtnMap);
	}

	@Override
	public JsonResult getInfo(String token) {
		if (token == null || "".equals(token)) {
			return new JsonResult(JsonResult.CODE_ERROR);
		}
		Map<String, Object> userInfo = baseMapper.getInfo(token);
		if (userInfo.size() == 0) {
			return new JsonResult(JsonResult.CODE_ERROR, "用户信息不存在");
		}
		ArrayList<Object> roleList = new ArrayList<>();
		roleList.add(userInfo.get("role"));
		userInfo.put("roles", roleList);
		return new JsonResult(JsonResult.CODE_SUCCESS, userInfo);
	}
}
