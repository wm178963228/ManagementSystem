package com.yatoooon.managementsystem.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yatoooon.managementsystem.service.IUserService;
import com.yatoooon.managementsystem.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@ApiOperation(value = "登录", notes = "")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult login(@RequestBody String jsonStr) {
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		String username = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		JsonResult jsonResult = userService.login(username,password);
		return jsonResult;
	}
}

