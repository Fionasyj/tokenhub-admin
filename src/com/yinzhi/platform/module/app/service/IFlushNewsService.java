package com.yinzhi.platform.module.app.service;

import java.util.List;
import java.util.Map;

import com.yinzhi.platform.entity.app.FlushNews;


public interface IFlushNewsService {
	
	
	/**
	 * 获取新闻列表
	 * 
	 */
	List<FlushNews> getFlushNewsList();
	
	/**
	 * 标记颜色
	 * @param flushId
	 * @param color
	 */
	void markColor(String flushId, String color);
	
	/**
	 * 获取分页信息
	 * @param flushNews
	 * @param start
	 * @param limit
	 * @return
	 */
	Map<String, Object> getPage(FlushNews flushNews, int start, int limit);
	
	/**
	 * 保存快讯
	 * @param flushNews
	 */
	void saveFlushNews(FlushNews flushNews);

	/**
	 * 根据id获取快讯实体
	 * @param flushnewsId
	 * @return
	 */
	FlushNews getFlushNews(String flushnewsId);
	
	/**
	 * 修改快讯
	 * @param flushNews
	 */
	void updateFlushNews(FlushNews flushNews);

	/**
	 * 禁用快讯
	 * @param flushId
	 */
	void forbidden(String flushId);
	
	/**
	 * 删除快讯
	 * @param flushnewsId
	 */
	void removeFlushNews(String flushnewsId);

	/**
	 * 启用快讯
	 * @param flushId
	 */
	void release(String flushId);
}
