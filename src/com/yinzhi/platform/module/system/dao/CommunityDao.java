package com.yinzhi.platform.module.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.system.Community;

@Repository
public class CommunityDao extends BaseDao {
	
	/**
	 * 根据小区ID获取小区集合
	 * @param id
	 * @return
	 */
	public List<Community> getCommunityListById(int id){		
		String sql = "select cmid,cmname,pname,phone,address,cid from t_community where cmid=?";
		Object[] args = new Object[]{
				id
		};
		return this.getList(sql, args, Community.class);
	}
 
}
