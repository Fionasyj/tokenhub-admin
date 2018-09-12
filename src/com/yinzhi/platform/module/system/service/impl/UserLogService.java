package com.yinzhi.platform.module.system.service.impl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.entity.system.UserLog;
import com.yinzhi.platform.module.system.dao.UserLogDao;
import com.yinzhi.platform.module.system.service.IUserLogService;


@Service
public class UserLogService implements IUserLogService{
	
	@Autowired
	private UserLogDao userLogDao;

	public Map<String, Object> getPage(UserLog userLog, int start, int limit) {
		return this.userLogDao.getPage(userLog, start, limit);
	}


}
