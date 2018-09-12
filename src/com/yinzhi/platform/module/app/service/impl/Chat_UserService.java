package com.yinzhi.platform.module.app.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.entity.app.Chat_User;
import com.yinzhi.platform.module.app.dao.Chat_UserDao;
import com.yinzhi.platform.module.app.service.IChat_UserService;

@Service
public class Chat_UserService implements IChat_UserService{
	
	@Autowired
	private Chat_UserDao chat_userDao;
	
	@Override
	public Chat_User getChat_User(String userId) {
		return this.chat_userDao.getChat_User(userId);
	}

	@Override
	public Map<String, Object> getPage(String chat_roomId, String nick,String status, int start, int limit) {
		return this.chat_userDao.getPage(chat_roomId, nick,status, start, limit);
	}

	@Override
	public List<Chat_User> getChat_UserList() {
		return this.chat_userDao.getChat_UserList();

	}

	@Override
	public void forbid(String userId) {
		this.chat_userDao.forbid(userId);
		
	}

	@Override
	public void release(String userId) {
		this.chat_userDao.release(userId);
	}
}
