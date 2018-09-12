package com.yinzhi.platform.module.system.service;

import java.util.List;
import java.util.Map;

import com.yinzhi.platform.entity.system.Resource;
import com.yinzhi.platform.entity.system.User;



public interface IUserService {
	
	/**
	 * 返回用户实体信息
	 * @param userId
	 * @return
	 */
	User getUser(String userId);
	
	/**
	 * 保存用户
	 * @param user
	 */
	void saveUser(User user);
	
	/**
	 * 修改用户
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * 删除用户
	 * @param userId
	 */
	void removeUser(String userId);
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param pass
	 */
	void updateUserPass(String userId, String pass);
	
	/**
	 * 用户分页列表
	 * @param user
	 * @param start
	 * @param limit
	 * @return
	 */
	Map<String, Object> getPage(User user, int start, int limit);
	
	/**
	 * 用户登陆
	 * @param account
	 * @param pass
	 * @return
	 */
	User userLogin(String account, String pass);
	
	/**
	 * 返回用户对应角色选中状态树结构列表
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> getUserRoleCheckList(String userId);
	
	/**
	 * 保存用户对应角色
	 * @param userId
	 * @param roleId
	 */
	void saveUserRole(String userId, String roleIds, String roleNames);
	
	/**
	 * 返回用户角色对应资源列表
	 * @param userId
	 * @return
	 */
	List<Resource> getUserResourceList(String userId);
	/**
	 * 获取在登录页面可以登录的用户
	 * @param loginType
	 * @return
	 */
	List<User> getLoignTypeUserList(String loginType);
}
