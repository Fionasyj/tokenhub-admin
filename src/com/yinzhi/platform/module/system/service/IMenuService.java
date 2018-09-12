package com.yinzhi.platform.module.system.service;

import java.util.List;
import java.util.Map;

import com.yinzhi.platform.entity.system.Menu;


public interface IMenuService {
	
	/**
	 * 返回菜单实体信息
	 * @param menuId
	 * @return
	 */
	Menu getMenu(String menuId);
	
	/**
	 * 返回子菜单树结构信息
	 * @param menuId
	 * @return
	 */
	List<Map<String, Object>> getMenuListByPid(String menuId);
	
	/**
	 * 返回用户角色分配菜单信息
	 * @param userId
	 * @param menuId
	 * @return
	 */
	List<Map<String, Object>> getUserMenuList(String userId, String menuId);
	
	/**
	 * 保存菜单
	 * @param menu
	 */
	void saveMenu(Menu menu);
	
	/**
	 * 删除菜单
	 * @param menuId
	 */
	void removeMenu(String menuId);
	
	/**
	 * 修改菜单
	 * @param menu
	 */
	void updateMenu(Menu menu);
}
