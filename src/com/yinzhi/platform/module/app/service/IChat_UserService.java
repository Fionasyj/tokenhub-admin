package com.yinzhi.platform.module.app.service;

import java.util.List;
import java.util.Map;

import com.yinzhi.platform.entity.app.Chat_User;

public interface IChat_UserService {
	
	/**
	 * 返回用户实体信息
	 * @param userId
	 * @return
	 */
	Chat_User getChat_User(String userId);
	
	/**
	 * 用户分页列表
	 * @param user
	 * @param start
	 * @param limit
	 * @return
	 */
	Map<String, Object> getPage(String chat_roomId, String nick,String status,int start, int limit);
	
	/**
	 * 获取所有聊天室用户资源
	 * @param user
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Chat_User> getChat_UserList();

	/**
	 * 禁言用户
	 * @param userId
	 * @return
	 */
	void forbid(String userId);

	/**
	 * 解禁用户
	 * @param userId
	 * @return
	 */
	void release(String userId);
}
