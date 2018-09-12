package com.yinzhi.platform.module.app.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.entity.app.FlushNews;
import com.yinzhi.platform.module.app.dao.FlushNewsDao;
import com.yinzhi.platform.module.app.service.IFlushNewsService;


@Service
public class FlushNewsService implements IFlushNewsService{
	
	@Autowired
	private FlushNewsDao flushNewsDao;

	@Override
	public List<FlushNews> getFlushNewsList() {
		return this.flushNewsDao.getFlushNewsList();

	}

	@Override
	public void markColor(String flushId, String color) {
		this.flushNewsDao.markColor(flushId, color);
		
	}

	@Override
	public Map<String, Object> getPage(FlushNews flushNews, int start, int limit) {
		return this.flushNewsDao.getPage(flushNews, start, limit);
	}

	@Override
	public void saveFlushNews(FlushNews flushNews) {
		this.flushNewsDao.saveFlushNews(flushNews);
		
	}

	@Override
	public FlushNews getFlushNews(String flushnewsId) {
		return this.flushNewsDao.getFlushNews(flushnewsId);
	}

	@Override
	public void updateFlushNews(FlushNews flushNews) {
		this.flushNewsDao.updateFlushNews(flushNews);
		
	}

	@Override
	public void forbidden(String flushId) {
		this.flushNewsDao.forbidden(flushId);
		
	}

	@Override
	public void removeFlushNews(String flushnewsId) {
		this.flushNewsDao.removeFlushNews(flushnewsId);
		
	}

	@Override
	public void release(String flushId) {
		this.flushNewsDao.release(flushId);
		
	}

}
