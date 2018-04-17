package com.yatoooon.managementsystem.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.yatoooon.managementsystem.model.User;
import com.yatoooon.managementsystem.dao.UserMapper;
import com.yatoooon.managementsystem.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yatoooon.managementsystem.utils.JsonResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		user.setUsername(username);
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

	@Override
	public JsonResult logout(HttpSession session) {
		if (session == null || "".equals(session.getId())) {
			return new JsonResult(JsonResult.CODE_ERROR, "退出失败");
		}
		User user = new User();
		user.setToken(session.getId());
		User one = baseMapper.selectOne(user);
		one.setToken(" ");
		Integer integer = baseMapper.updateById(one);
		session.invalidate();
		if (integer != 1) {
			return new JsonResult(JsonResult.CODE_ERROR);
		}
		return new JsonResult(JsonResult.CODE_SUCCESS, "退出成功");
	}

	@Override
	public JsonResult getUserList(Page<User> page, Integer state) {
		Map<String, Object> rtnMap = new HashMap<>();
		// 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
		page.setOptimizeCountSql(false);
		// 不查询总记录数
		page.setSearchCount(true);
		Page<User> userPage = page.setRecords(baseMapper.selectUserList(page, state));
		long total = page.getTotal();
		rtnMap.put("userList", userPage.getRecords());
		rtnMap.put("total", total);
		return new JsonResult(JsonResult.CODE_SUCCESS, rtnMap);
	}

	@Override
	public JsonResult addUser(Integer roleid, String username, String password, String avatar, Integer state) {
		if (username == null || "".equals(username) || password == null || "".equals(password)) {
			return new JsonResult(JsonResult.CODE_ERROR);
		}
		User user = new User();
		user.setUsername(username);
		User one = baseMapper.selectOne(user);
		if (one != null) {
			return new JsonResult(JsonResult.CODE_ERROR, "已存在该用户名");
		}
		user.setRoleId(roleid);
		user.setPassword(password);
		user.setAvatar(avatar);
		user.setState(state);
		user.setCreateTime(new Date());
		Integer insert = baseMapper.insert(user);
		if (insert != 1) {
			return new JsonResult(JsonResult.CODE_ERROR, "新建用户失败");
		}
		return new JsonResult(JsonResult.CODE_SUCCESS);
	}

}
