package com.yinzhi.platform.module.system.service;

import java.util.List;

import com.yinzhi.platform.entity.system.Community;

public interface ICommunityService {
	
	/**
	 * 根据小区ID获取小区集合
	 * @param id
	 * @return
	 */
	public List<Community> getCommunityListById(int id);
	
}
