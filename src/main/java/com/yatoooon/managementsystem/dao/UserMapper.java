package com.yatoooon.managementsystem.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yatoooon.managementsystem.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yatoooon
 * @since 2018-03-14
 */
public interface UserMapper extends BaseMapper<User> {

	List<Map<String, Object>> selectMyPage( @Param("ew") Wrapper<User> wrapper);


	Map<String, Object> getInfo(String token);
}
