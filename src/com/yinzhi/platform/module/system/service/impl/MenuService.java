package com.yinzhi.platform.module.system.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.core.AppUtil;
import com.yinzhi.platform.entity.system.Menu;
import com.yinzhi.platform.module.system.dao.MenuDao;
import com.yinzhi.platform.module.system.service.IMenuService;



@Service
public class MenuService implements IMenuService{
	
	@Autowired
	private MenuDao menuDao;

	public List<Map<String, Object>> getMenuListByPid(String menuId) {
		
		return this.menuDao.getMenuListByPid(menuId);
	}

	public void saveMenu(Menu menu) {
		menu.setId(AppUtil.getUUID());
		menu.setLeaf(1);
		this.menuDao.saveMenu(menu);
		
		Menu pmenu = new Menu();
		pmenu.setId(menu.getPid());
		pmenu.setLeaf(null);
		pmenu.setState("closed");
		this.menuDao.updateMenuLeaf(pmenu);
	}

	public void removeMenu(String menuId) {
		Menu menu = this.menuDao.getMenu(menuId);
		
		//查询当前菜单下面是否有子级，有子级将不能删除
		int count = this.menuDao.getMenuChildCount(menuId);
		if(count > 0){
			throw new RuntimeException(AppUtil.getExMsg("当前菜单存在子级，不能直接删除！"));
		}
		this.menuDao.removeMenu(menuId);
		
		
		//如果原父级部门下面已经没有子级，将更新子级状态
		int pcount = this.menuDao.getMenuChildCount(menu.getPid());
		if(pcount == 0){
			Menu pMenu = new Menu();
			pMenu.setId(menu.getPid());
			pMenu.setLeaf(1);
			pMenu.setState("open");
			this.menuDao.updateMenuLeaf(pMenu);
		}
	}

	public List<Map<String, Object>> getUserMenuList(String userId,
			String menuId) {
		List<Map<String, Object>> list = this.menuDao.getUserMenuList(userId, menuId);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if((Integer)map.get("type") == 1){
				map.put("leaf", false);
				map.put("state", "closed");
			}
			else if((Integer)map.get("type") == 2){
				map.put("leaf", true);
				map.put("state", "open");
			}
			
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("url", map.get("url"));
			map.put("attributes", m);
			resultList.add(map);
		}
		return resultList;
	}

	public void updateMenu(Menu menu) {
		//获取当前菜单更新前的数据
		Menu ymenu = this.menuDao.getMenu(menu.getId());
		
		this.menuDao.updateMenu(menu);
		
		//更新父级状态
		Menu pmenu = new Menu();
		pmenu.setId(menu.getPid());
		pmenu.setLeaf(null);
		pmenu.setState("closed");
		this.menuDao.updateMenuLeaf(pmenu);
		
		//如果原父级部门下面已经没有子级，将更新状态
		int count = this.menuDao.getMenuChildCount(ymenu.getPid());
		if(count == 0){
			Menu ypMenu = new Menu();
			ypMenu.setId(ymenu.getPid());
			ypMenu.setLeaf(1);
			ypMenu.setState("open");
			this.menuDao.updateMenuLeaf(ypMenu);
		}
	}

	public Menu getMenu(String menuId) {
		return this.menuDao.getMenu(menuId);
	}

}
