package com.yatoooon.managementsystem.service;

import com.yatoooon.managementsystem.model.User;
import com.baomidou.mybatisplus.service.IService;
import com.yatoooon.managementsystem.utils.JsonResult;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yatoooon
 * @since 2018-03-14
 */
public interface IUserService extends IService<User> {

	JsonResult login(String username, String password, HttpSession session);

	JsonResult getInfo(String token);
}
