package com.yinzhi.platform.module.system.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.system.UserLog;
import com.yinzhi.platform.global.DateUtil;

@Repository
public class UserLogDao extends BaseDao {
	
	/**
	 * 列表分页
	 * @param start
	 * @param limit
	 * @return
	 */
	public Map<String, Object> getPage(UserLog userLog, int start, int limit){
		StringBuffer sb = new StringBuffer();
		sb.append("select u.account,u.name userName,ul.ip,ul.date,r.name resourceName,r.url ");
		sb.append(" from s_user u, s_resource r, s_user_log ul where u.id=ul.userId and r.id=ul.resourceId ");
		
		
		List<Object> list = new ArrayList<Object>();
		if(userLog.getAccount() != null && !"".equals(userLog.getAccount())){
			sb.append(" and u.account like ? ");
			list.add(userLog.getAccount() + "%");
		}
		
		if(userLog.getDate() != null && !"".equals(userLog.getDate())){
			sb.append(" and ul.date>=? and ul.date<=?");
			list.add(userLog.getDate());
			list.add(DateUtil.dateToString(userLog.getDate(), "yyyy-MM-dd") + " 23:59:59");
		}
		
		sb.append(" order by date desc");

		return this.getPage(sb.toString(), list.toArray(), start, limit);
	}
	

}
