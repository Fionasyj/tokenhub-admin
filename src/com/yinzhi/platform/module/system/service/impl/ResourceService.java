package com.yinzhi.platform.module.system.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.entity.system.Resource;
import com.yinzhi.platform.entity.system.UserLog;
import com.yinzhi.platform.module.system.dao.ResourceDao;
import com.yinzhi.platform.module.system.service.IResourceService;


@Service
public class ResourceService implements IResourceService{
	
	@Autowired
	private ResourceDao resourceDao;

	public Map<String, Object> getPage(int start, int limit) {
		return this.resourceDao.getPage(start, limit);
	}

	public void saveResource(Resource resource) {
		this.resourceDao.saveResource(resource);
	}

	public void removeResource(String resourceId) {
		this.resourceDao.removeResource(resourceId);
	}

	public Resource getResource(String resourceId) {
		return this.resourceDao.getResource(resourceId);
	}
	
	public Resource getResourceByUrl(String url) {
		return this.resourceDao.getResourceByUrl(url);
	}

	public List<Resource> getResourceList() {
		return this.resourceDao.getResourceList();
	}


	public void updateResource(Resource resource) {
		this.resourceDao.updateResource(resource);
	}

	public void saveUserLog(UserLog userLog) {
		this.resourceDao.saveUserLog(userLog);
	}

}
