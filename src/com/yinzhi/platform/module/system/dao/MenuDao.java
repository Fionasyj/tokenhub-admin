package com.yinzhi.platform.module.system.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.system.Menu;

@Repository
public class MenuDao extends BaseDao {
	
	/**
	 * 返回菜单实体信息
	 * @param menuId
	 * @return
	 */
	public Menu getMenu(String menuId){
		return this.get(menuId, Menu.class);
	}
	
	/**
	 * 返回子菜单树结构信息
	 * @param menuId
	 * @return
	 */
	public List<Map<String, Object>> getMenuListByPid(String menuId){
		String sql = "select m.id,m.soft,text,leaf,iconCls,r.url,'1' expanded,iframe,type,param,state from s_menu m, s_resource r";
		sql += " where m.resourceid=r.id and pid=? order by m.soft";
		Object[] args = new Object[]{menuId};
		return this.queryForList(sql, args);
	}
	
	
	/**
	 * 返回下级菜单数量
	 * @param menuId
	 * @return
	 */
	public int getMenuChildCount(String menuId){
		String sql = "select count(0) from s_menu where pid=?";
		Object[] args = new Object[]{
			menuId
		};
		return this.queryForInt(sql, args);
	}
	
	
	/**
	 * 返回用户角色分配菜单信息
	 * @param userId
	 * @param menuId
	 * @return
	 */
	public List<Map<String, Object>> getUserMenuList(String userId, String menuId){
		String sql = "select m.id,text,leaf,iconCls,r.url,expanded,iframe,type,param,state from s_menu m, s_resource r";
		sql += " where m.resourceid=r.id and pid=? and type<>3 ";
		sql += " and m.id in ( SELECT menuId FROM s_role_menu where roleId in ( SELECT roleId from s_user_role WHERE userId=? )) order by m.soft";
		Object[] args = new Object[]{menuId, userId};
		return this.queryForList(sql, args);
	}
	
	/**
	 * 保存菜单
	 * @param menu
	 */
	public void saveMenu(Menu menu){
		this.insert(menu);
	}
	
	/**
	 * 修改菜单是否有子级状态
	 * @param menu
	 */
	public void updateMenuLeaf(Menu menu){
		String sql = "update s_menu set leaf=?,state=? where id=?";
		Object[] args = new Object[] {menu.getLeaf(),menu.getState(), menu.getId() };

		this.update(sql, args);
	}
	
	/**
	 * 修改菜单
	 * @param menu
	 */
	public void updateMenu(Menu menu){
		String sql = "update s_menu set text=?,iconCls=?,pid=?,iframe=?,expanded=?,soft=?,type=?,resourceId=?,param=? where id=?";
		Object[] args = new Object[] {
			menu.getText(), menu.getIconCls(), menu.getPid(),
			menu.getIframe(),menu.getExpanded() ,menu.getSoft(),
			menu.getType(),menu.getResourceId(), menu.getParam(),
			menu.getId()
		};

		this.update(sql, args);
	}
	
	/**
	 * 删除菜单
	 * @param menuId
	 */
	public void removeMenu(String menuId) {
		String sql = "delete from s_menu where id=?";
		Object[] args = new Object[] { menuId };
		this.update(sql, args);
	}
}
