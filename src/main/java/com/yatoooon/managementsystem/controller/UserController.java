package com.yatoooon.managementsystem.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.yatoooon.managementsystem.model.User;
import com.yatoooon.managementsystem.service.IUserService;
import com.yatoooon.managementsystem.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yatoooon
 * @since 2018-03-14
 */
@Api(value = "UserController", description = "关于用户")
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@ApiOperation(value = "系统登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult login(@RequestBody String jsonStr, HttpSession session) {
		try {
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			String username = jsonObject.getString("username");
			String password = jsonObject.getString("password");
			return userService.login(username, password, session);
		} catch (Exception e) {
			return new JsonResult(JsonResult.CODE_ERROR, e.getMessage());
		}
	}


	@ApiOperation(value = "系统退出")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult logout(HttpSession session) {
		try {
			return userService.logout(session);
		} catch (Exception e) {
			return new JsonResult(JsonResult.CODE_ERROR, e.getMessage());
		}
	}

	@ApiOperation(value = "用户信息")
	@RequestMapping(value = "/getInfo", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getInfo(@RequestBody String jsonStr) {
		try {
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			String token = jsonObject.getString("token");
			return userService.getInfo(token);
		} catch (Exception e) {
			return new JsonResult(JsonResult.CODE_ERROR, e.getMessage());
		}
	}

	@ApiOperation(value = "用户列表")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getUserList(@RequestBody String jsonStr) {
		try {
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			Integer page = jsonObject.getInteger("page");
			Integer limit = jsonObject.getInteger("limit");
			String name = jsonObject.getString("name");
			Integer state = jsonObject.getInteger("state");
			Page<User> userPage = new Page<>(page, limit);
			return userService.getUserList(userPage, state);
		} catch (Exception e) {
			return new JsonResult(JsonResult.CODE_ERROR, e.getMessage());
		}

	}

	@ApiOperation(value = "添加用户")
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult addUser(@RequestBody String jsonStr) {
		try {
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			Integer roleid = jsonObject.getInteger("roleid");
			String username = jsonObject.getString("username");
			String password = jsonObject.getString("password");
			String avatar = jsonObject.getString("avatar");
			Integer state = jsonObject.getInteger("state");
			return userService.addUser(roleid, username, password, avatar, state);
		} catch (Exception e) {
			return new JsonResult(JsonResult.CODE_ERROR, e.getMessage());
		}

	}
}

