package com.yinzhi.platform.module.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.app.FlushNews;
@Repository
public class FlushNewsDao extends BaseDao {
	//实体字段
	private String field = this.getSelectField(FlushNews.class);
	/**
	 * 全部资源列表
	 * @return
	 */
	public List<FlushNews> getFlushNewsList(){
		String sql = "select " + field + " from flushNews order by created_at desc";
		return this.getList(sql, new Object[]{}, FlushNews.class);
	}
	
	/**
	 * 标记颜色
	 */
	public void markColor(String flushId, String color) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		field.append(" content_color='"+color+"'");
		sql.append("update flushnews set ");
		sql.append(field);
		sql.append(" where id="+flushId+" ");
		this.update(sql.toString());
		
	}
	
	/**
	 * 获取分页信息
	 * @param flushNews
	 * @param start
	 * @param limit
	 */
	public Map<String, Object> getPage(FlushNews flushNews, int start, int limit) {
		StringBuffer sql = new StringBuffer();
		sql.append("select " + field + " from flushNews where 1=1 ");
		StringBuffer where = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		if(!"".equals(flushNews.getContent()) && null != flushNews.getContent()){
			where.append(" and content like ?");
			list.add(flushNews.getContent() + "%");
		}
		sql.append(where).append(" order by created_at desc");
		return this.getPage(sql.toString(), list.toArray(), start, limit);
	}
	
	/**
	 * 保存快讯
	 * @param flushNews
	 */
	public void saveFlushNews(FlushNews flushNews) {
		this.insert(flushNews);
	}
	
	/**
	 * 返回快讯实体信息
	 * @param flushNews
	 */
	public FlushNews getFlushNews(String flushnewsId) {
		return this.get(flushnewsId, FlushNews.class);
	}
	
	/**
	 * 修改快讯
	 * @param flushNews
	 */
	public void updateFlushNews(FlushNews flushNews) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		field.append(" title=?,content=?, url=?,updated_at=?,status=?,source_media=?,source_id=?,content_color=?,push_times=?");
		List<Object> list = new ArrayList<Object>();
		
		list.add(flushNews.getTitle());
		list.add(flushNews.getContent());
		list.add(flushNews.getUrl());
		list.add(new Date().getTime()*1000);
		list.add(flushNews.getStatus());
		list.add(flushNews.getSource_media());
		list.add(flushNews.getSource_id());
		list.add(flushNews.getContent_color());
		list.add(flushNews.getPush_times());
		sql.append("update flushnews set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(flushNews.getId());
		this.update(sql.toString(), list.toArray());
	
		
	}
	
	/**
		 * 禁用快讯
		 * @param flushId
		 */
	public void forbidden(String flushId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		field.append("status=3 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update flushnews set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(flushId);  
		this.update(sql.toString(), list.toArray());
			
		}
	
	/**
	 * 删除快讯
	 * @param flushnewsId
	 */
	public void removeFlushNews(String flushnewsId) {
		String sql = "delete from flushnews where id=?";
		Object[] args = new Object[] { flushnewsId };
		this.update(sql, args);
		
	}
	
	/**
	 * 启用
	 * @param flushId
	 */
	public void release(String flushId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		field.append(" status=0 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update flushnews set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(flushId);  
		this.update(sql.toString(), list.toArray());
		
	}
		
}