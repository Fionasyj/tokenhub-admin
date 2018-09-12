package com.yinzhi.platform.module.system.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yinzhi.platform.core.AppUtil;
import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.system.Dept;
import com.yinzhi.platform.entity.system.Resource;
import com.yinzhi.platform.entity.system.User;

@Repository
public class UserDao extends BaseDao {
	//实体字段
	private String field = this.getSelectField(User.class);
	
	/**
	 * 返回用户实体信息
	 * @param userId
	 * @return
	 */
	public User getUser(String userId){
		return this.get(userId, User.class);
	}
	
	/**
	 * 根据帐号返回用户实体信息
	 * @param account
	 * @return
	 */
	public User getUserByAccount(String account){
		String sql = "select " + field + " from s_user where account=?";
		Object[] args = new Object[]{ account };
		return this.get(sql, args, User.class);
	}

	/**
	 * 用户分页列表
	 * @param user
	 * @param start
	 * @param limit
	 * @return
	 */
	public Map<String, Object> getPage(User user, int start, int limit) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select u.id,u.name,account,age,phone,ctime,status,deptId,d.name deptName,roleNames from s_user u, s_dept d");
		
		StringBuffer where = new StringBuffer();
		where.append(" where deptId in (" + user.getDeptIds() + ") and u.deptId=d.id and u.status in (0,1)");
		List<Object> list = new ArrayList<Object>();
		if(!"".equals(user.getName()) && null != user.getName()){
			where.append(" and u.name like ?");
			list.add(user.getName() + "%");
		}
		
		if(!"".equals(user.getAccount()) && null != user.getAccount()){
			where.append(" and u.account like ?");
			list.add(user.getAccount() + "%");
		}
		sql.append(where).append(" order by u.ctime desc");
		return this.getPage(sql.toString(), list.toArray(), start, limit);
	}

	/**
	 * 保存用户
	 * @param user
	 */
	public void saveUser(User user) {
		this.insert(user);
	}

	/**
	 * 删除用户
	 * @param userId
	 */
	public void removeUser(String userId) {
		String sql = "delete from s_user where id=?";
		Object[] args = new Object[] { userId };
		this.update(sql, args);
	}
	
	/**
	 * 返回用户对应角色选中状态树结构列表
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getUserRoleCheckList(String userId){
		String sql = "select id,name text,  (select count(*) from s_user_role ur where userId=? and roleId=r.id) checked from s_role r";
		return this.queryForList(sql, new Object[]{userId});
	}
	
	/**
	 * 返回用户角色对应资源列表
	 * @param userId
	 * @return
	 */
//	public List<Map<String, Object>> getUserResourceList(String userId){
//		String sql = "select re.* from s_user u, s_role r, s_user_role ur, s_role_menu rm, s_menu m, s_resource re";
//		sql += " where u.id=? and u.id=ur.userId and r.id=ur.roleId and m.id=rm.menuId and r.id=rm.roleId";
//		sql += " and m.resourceid=re.id";
//		return this.queryForList(sql, new Object[]{userId});
//	}
	
	public List<Resource> getUserResourceList(String userId){
		String sql = "select re.* from s_user u, s_role r, s_user_role ur, s_role_menu rm, s_menu m, s_resource re";
		sql += " where u.id=? and u.id=ur.userId and r.id=ur.roleId and m.id=rm.menuId and r.id=rm.roleId";
		sql += " and m.resourceid=re.id";
		return this.getList(sql, new Object[]{userId}, Resource.class);
	}
	/**
	 * 返回所有可以在登录页面获取 的用户
	 * @return
	 */
	public List<User> getLoignTypeUserList(String loginType){
		String sql = "select id,name text from s_user where loginType=？";
		Object[] args = new Object[]{
			loginType
		};
		return this.getList(sql, args,User.class);
	}
	/**
	 * 删除用户对应角色
	 * @param userId
	 */
	public void removeUserRole(String userId){
		String sql = "delete from s_user_role where userId=?";
		Object[] args = new Object[] { userId };
		this.update(sql, args);
	}
	
	/**
	 * 保存用户对应角色
	 * @param userId
	 * @param roleId
	 */
	public void saveUserRole(String userId, String roleId){
		String sql = "insert into s_user_role(userId, roleId) values(?,?)";
		Object[] args = new Object[] { userId, roleId };
		this.update(sql, args);
	}
	
	/**
	 * 修改用户
	 * @param user
	 */
	public void updateUser(User user){
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		field.append(" account=?,name=?, age=?,phone=?,utime=?,status=?,deptId=?");
		List<Object> list = new ArrayList<Object>();
		
		list.add(user.getAccount());
		list.add(user.getName());
		list.add(user.getAge());
		list.add(user.getPhone());
		list.add(user.getUtime());
		list.add(user.getStatus());
		list.add(user.getDeptId());
		
		if(user.getPass() != null && !"".equals(user.getPass())){
			field.append(",pass=?");
			list.add(AppUtil.md5(user.getPass()));
		}
		sql.append("update s_user set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(user.getId());
		
		
		this.update(sql.toString(), list.toArray());
	}
	
	public void updateUserRoleNames(String userId, String roleNames){
		String sql = "update s_user set roleNames=? where id=?";
		this.update(sql, new Object[]{roleNames, userId});
	}
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param pass
	 */
	public void updateUserPass(String userId, String pass){
		String sql = "update s_user set pass=? where id=?";
		Object[] args = new Object[] { AppUtil.md5(pass), userId };
		this.update(sql, args);
	}
}