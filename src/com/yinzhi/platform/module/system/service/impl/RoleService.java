package com.yinzhi.platform.module.system.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.entity.system.Role;
import com.yinzhi.platform.module.system.dao.RoleDao;
import com.yinzhi.platform.module.system.service.IRoleService;


@Service
public class RoleService implements IRoleService{
	
	@Autowired
	private RoleDao roleDao;

	public Map<String, Object> getPage(int start, int limit) {
		
		return this.roleDao.getPage(start, limit);
	}

	public void saveRole(Role role) {
		this.roleDao.saveRole(role);
	}

	public void removeRole(String roleId) {
		this.roleDao.removeRole(roleId);
	}

	public List<Map<String, Object>> getRoleMenuCheckList(String roleId,
			String menuId) {
		List<Map<String, Object>> list = this.roleDao.getRoleMenuCheckList(roleId, menuId);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if(Integer.parseInt(map.get("checked").toString()) > 0){
				map.put("checked", true);
			}
			else{
				map.put("checked", false);
			}
			map.put("expanded", true);
			resultList.add(map);
		}
		return resultList;
	}

	public void saveRoleMenu(String roleId, String menuIds) {
		
		this.roleDao.removeRoleMenu(roleId);
		
		if(!"".equals(menuIds)){
			String[] arrMenuId = menuIds.split(",");

			for (String menuId : arrMenuId) {
				this.roleDao.saveRoleMenu(roleId, menuId);
			}
		}
		
	}

	public Role getRole(String roleId) {
		return this.roleDao.getRole(roleId);
	}

	public void updateRole(Role role) {
		this.roleDao.updateRole(role);
	}


}
