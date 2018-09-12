package com.yinzhi.platform.module.system.service;

import java.util.List;
import java.util.Map;

import com.yinzhi.platform.entity.system.Resource;
import com.yinzhi.platform.entity.system.UserLog;


public interface IResourceService {
	
	Resource getResource(String resourceId);
	
	Resource getResourceByUrl(String url);
	
	
	Map<String, Object> getPage(int start, int limit);
	
	void saveResource(Resource resource);
	
	void updateResource(Resource resource);
	
	void removeResource(String resourceId);
	
	public void saveUserLog(UserLog userLog);
	
	List<Resource> getResourceList();
}
