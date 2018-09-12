package com.yinzhi.platform.module.system.service;

import java.util.List;
import java.util.Map;

import com.yinzhi.platform.entity.system.Role;


public interface IRoleService {
	Role getRole(String roleId);
	
	void updateRole(Role role);
	
	Map<String, Object> getPage(int start, int limit);
	
	void saveRole(Role role);
	
	void removeRole(String roleId);
	
	List<Map<String, Object>> getRoleMenuCheckList(String roleId, String menuId);
	
	void saveRoleMenu(String roleId, String menuIds);
	
	
}
