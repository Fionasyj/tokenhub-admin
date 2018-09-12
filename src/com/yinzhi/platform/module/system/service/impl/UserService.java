package com.yinzhi.platform.module.system.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.core.AppUtil;
import com.yinzhi.platform.entity.system.Resource;
import com.yinzhi.platform.entity.system.User;
import com.yinzhi.platform.module.system.dao.UserDao;
import com.yinzhi.platform.module.system.service.IUserService;



@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserDao userDao;

	public Map<String, Object> getPage(User user, int start, int limit) {
		return this.userDao.getPage(user, start, limit);
	}

	public void saveUser(User user) {
		User u = this.userDao.getUserByAccount(user.getAccount());
		if(u != null){
			throw new RuntimeException(AppUtil.getExMsg("该用户名已存在！"));
		}
		user.setPass(AppUtil.md5(user.getPass()));
		this.userDao.saveUser(user);
	}

	public void removeUser(String userId) {
		this.userDao.removeUser(userId);
	}

	public User userLogin(String account, String pass) {
		User user = this.userDao.getUserByAccount(account);
		if(null == user){
			throw new RuntimeException(AppUtil.getExMsg("用户名不存在！"));
		}
		
		if(!user.getPass().equals(AppUtil.md5(pass))){
			throw new RuntimeException(AppUtil.getExMsg("密码不正确！"));
		}
		
		if(user.getStatus().equals(1)){
			throw new RuntimeException(AppUtil.getExMsg("该用户已停用，无法登陆，请联系管理员！"));
		}
		
		if(user.getStatus().equals(2)){
			throw new RuntimeException(AppUtil.getExMsg("该用户已注销，无法登陆，请联系管理员！"));
		}
		
		return user;
	}

	public List<Map<String, Object>> getUserRoleCheckList(String userId) {
		List<Map<String, Object>> list = this.userDao.getUserRoleCheckList(userId);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if(Integer.parseInt(map.get("checked").toString()) > 0){
				map.put("checked", true);
			}
			else{
				map.put("checked", false);
			}
			map.put("leaf", true);
			resultList.add(map);
		}
		return resultList;
	}

	public void saveUserRole(String userId, String roleIds, String roleNames) {
		
		this.userDao.removeUserRole(userId);
		
		if(!"".equals(roleIds)){
			String[] arrRoleId = roleIds.trim().split(",");
			for (String roleId : arrRoleId) {
				this.userDao.saveUserRole(userId, roleId);
			}
		}
		
		this.userDao.updateUserRoleNames(userId, roleNames);
	}

	public List<Resource> getUserResourceList(String userId) {
		return this.userDao.getUserResourceList(userId);
	}

	public User getUser(String userId) {
		return this.userDao.getUser(userId);
	}

	public void updateUser(User user) {
		user.setUtime(new Date());
		this.userDao.updateUser(user);
	}

	public void updateUserPass(String userId, String pass) {
		this.userDao.updateUserPass(userId, pass);
	}

	@Override
	public List<User> getLoignTypeUserList(String loginType) {
		return this.userDao.getLoignTypeUserList(loginType);
	}

}
