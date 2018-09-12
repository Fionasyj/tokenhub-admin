package com.yinzhi.platform.module.system.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.system.Resource;
import com.yinzhi.platform.entity.system.UserLog;

@Repository
public class ResourceDao extends BaseDao {
	//实体字段
	private String field = this.getSelectField(Resource.class);
	
	/**
	 * 返回资源实体信息
	 * @param resourceId
	 * @return
	 */
	public Resource getResource(String resourceId){
		return this.get(resourceId, Resource.class);
	}
	
	/**
	 * 根据url返回资源实体信息
	 * @param url
	 * @return
	 */
	public Resource getResourceByUrl(String url){
		String sql = "select " + field + " from s_resource where url=?";
		Object[] args = new Object[]{ url };
		return this.get(sql, args, Resource.class);
	}

	/**
	 * 列表分页
	 * @param start
	 * @param limit
	 * @return
	 */
	public Map<String, Object> getPage(int start, int limit){
		String sql = "select " + field + " from s_resource order by name";
		return this.getPage(sql, new Object[]{}, start, limit);
	}
	
	/**
	 * 保存资源
	 * @param resource
	 */
	public void saveResource(Resource resource){
		this.insert(resource);
	}
	
	/**
	 * 保存用户对资源的操作日志
	 * @param userLog
	 */
	public void saveUserLog(UserLog userLog){
		this.insert(userLog);
	}
	
	/**
	 * 修改资源
	 * @param resource
	 */
	public void updateResource(Resource resource){
		this.update(resource);
	}
	
	/**
	 * 删除资源
	 * @param resourceId
	 */
	public void removeResource(String resourceId){
		String sql = "delete from s_resource where id=?";
		Object[] args = new Object[]{ resourceId };
		this.update(sql, args);
	}
	
	/**
	 * 全部资源列表
	 * @return
	 */
	public List<Resource> getResourceList(){
		String sql = "select " + field + " from s_resource order by name";
		return this.getList(sql, new Object[]{}, Resource.class);
	}
}
