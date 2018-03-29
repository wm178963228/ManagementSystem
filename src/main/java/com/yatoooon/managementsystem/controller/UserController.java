package com.yatoooon.managementsystem.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
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
		} catch (JSONException e) {
			return new JsonResult(JsonResult.CODE_ERROR, e.getMessage());
		}
	}


	@ApiOperation(value = "系统退出")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult login(HttpSession session) {
		try {
			session.invalidate();
			return new JsonResult(JsonResult.CODE_SUCCESS, "退出成功");
		} catch (Exception e) {
			return new JsonResult(JsonResult.CODE_ERROR, e.getMessage());
		}
	}

	@ApiOperation(value = "User信息")
	@RequestMapping(value = "/getInfo", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getInfo(@RequestBody String jsonStr) {
		try {
			JSONObject jsonObject = JSON.parseObject(jsonStr);
			String token = jsonObject.getString("token");
			return userService.getInfo(token);
		} catch (JSONException e) {
			return new JsonResult(JsonResult.CODE_ERROR, e.getMessage());
		}
	}
}

