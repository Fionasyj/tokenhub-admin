package com.yinzhi.platform.module.app.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.entity.app.AppUser;
import com.yinzhi.platform.module.app.dao.AppUserDao;
import com.yinzhi.platform.module.app.service.IAppUserService;



@Service
public class AppUserService implements IAppUserService{
	
	@Autowired
	private AppUserDao userDao;



	public List<AppUser> getUserList() {
		return this.userDao.getUserList();

	}


	public void forbiddenUser(String userId) {
		this.userDao.forbiddenUser(userId);
	}


	@Override
	public void release(String userId) {
		this.userDao.release(userId);
		
	}


	@Override
	public Map<String, Object> getPage(AppUser appUser, int start, int limit) {
		return this.userDao.getPage(appUser, start, limit);
	}

}
