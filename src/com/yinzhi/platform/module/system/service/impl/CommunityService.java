package com.yinzhi.platform.module.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.entity.system.Community;
import com.yinzhi.platform.module.system.dao.CommunityDao;
import com.yinzhi.platform.module.system.service.ICommunityService;
@Service
public class CommunityService implements ICommunityService {
	@Autowired
	private CommunityDao dao;

	@Override
	public List<Community> getCommunityListById(int id) {
		
		return dao.getCommunityListById(id);
	}
	
	
}
