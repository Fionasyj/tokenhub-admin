package com.yinzhi.platform.module.app.service;

import java.util.List;
import java.util.Map;

import com.yinzhi.platform.entity.app.AppUser;

public interface IAppUserService {
	/**
	 * 返回用户列表
	 * @param userId
	 * @return
	 */
	List<AppUser> getUserList();
	
	/**
	 * 禁用用户
	 * @param user
	 */
	void forbiddenUser(String userId);

	/**
	 * 解禁
	 * @param userId
	 */
	void release(String userId);

	/**
	 * 获取分页信息
	 * @param appUser
	 * @param start
	 * @param limit
	 * @return
	 */
	Map<String, Object> getPage(AppUser appUser, int start, int limit);
}
