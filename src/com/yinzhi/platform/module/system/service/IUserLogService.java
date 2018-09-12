package com.yinzhi.platform.module.system.service;

import java.util.Map;

import com.yinzhi.platform.entity.system.UserLog;


public interface IUserLogService {
	
	
	Map<String, Object> getPage(UserLog userLog, int start, int limit);
	

}
