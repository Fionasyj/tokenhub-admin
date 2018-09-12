package com.yinzhi.platform.module.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.app.AppUser;
@Repository
public class AppUserDao extends BaseDao {
	//实体字段
	private String field = this.getSelectField(AppUser.class);
	
	/**
	 * 禁用用户
	 * @param user
	 */
	public void forbiddenUser(String userId){
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		Long updated_at = new Date().getTime();
		field.append(" updated_at="+updated_at+",status=3 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update user set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(userId);
		this.update(sql.toString(), list.toArray());
	}
	
	/**
	 * 全部资源列表
	 * @return
	 */
	public List<AppUser> getUserList(){
		String sql = "select " + field + " from user where 1=1 order by id";
		return this.getList(sql, new Object[]{}, AppUser.class);
	}
	
	/**
	 * 解禁用户
	 * @param userId
	 */
	public void release(String userId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		Long updated_at = new Date().getTime();
		field.append(" updated_at="+updated_at+",status=0 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update user set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(userId);
		this.update(sql.toString(), list.toArray());
		
	}

	public Map<String, Object> getPage(AppUser appUser, int start, int limit) {
		StringBuffer sql = new StringBuffer();
		sql.append("select " + field + " from user order by id");
		List<Object> list = new ArrayList<Object>();
		return this.getPage(sql.toString(), list.toArray(), start, limit);
	}
}