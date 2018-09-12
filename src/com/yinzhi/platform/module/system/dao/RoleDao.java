package com.yinzhi.platform.module.system.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.system.Role;

@Repository
public class RoleDao extends BaseDao {
	
	/**
	 * 返回角色实体信息
	 * @param roleId
	 * @return
	 */
	public Role getRole(String roleId){
		return this.get(roleId, Role.class);
	}

	/**
	 * 角色分页列表
	 * @param start
	 * @param limit
	 * @return
	 */
	public Map<String, Object> getPage(int start, int limit){
		String sql = "select id,name,status from s_role";
		return this.getPage(sql, new Object[]{}, start, limit);
	}
	
	
	/**
	 * 保存角色
	 * @param role
	 */
	public void saveRole(Role role){
		this.insert(role);
	}
	
	/**
	 * 修改角色
	 * @param role
	 */
	public void updateRole(Role role){
		this.update(role);
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 */
	public void removeRole(String roleId){
		String sql = "delete from s_role where id=?";
		Object[] args = new Object[]{ roleId };
		this.update(sql, args);
	}
	
	/**
	 * 返回角色对应菜单选中树结构列表
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public List<Map<String, Object>> getRoleMenuCheckList(String roleId, String menuId){
		String sql = "select id, text, leaf, iconCls,expanded,state,";
		sql += " (select count(*) from s_role_menu rm where roleId=? and menuId=m.id) checked";
		sql += " from s_menu m where pid=? order by m.soft";
		return this.queryForList(sql, new Object[]{roleId, menuId});
	}
	
	/**
	 * 删除角色分配的菜单
	 * @param roleId
	 */
	public void removeRoleMenu(String roleId){
		String sql = "delete from s_role_menu where roleId=?";
		Object[] args = new Object[] { roleId };
		this.update(sql, args);
	}
	
	/**
	 * 保存角色对应菜单
	 * @param roleId
	 * @param menuId
	 */
	public void saveRoleMenu(String roleId, String menuId){
		String sql = "insert into s_role_menu(roleId, menuId) values(?,?)";
		Object[] args = new Object[] { roleId, menuId };
		this.update(sql, args);
	}
}
