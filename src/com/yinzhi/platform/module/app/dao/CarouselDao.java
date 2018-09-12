package com.yinzhi.platform.module.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.app.Carousel;
import com.yinzhi.platform.entity.app.FlushNews;
@Repository
public class CarouselDao extends BaseDao {
	//实体字段
	private String field = this.getSelectField(Carousel.class);
	
	/**
	 * 全部资源列表
	 * @return
	 */
	public List<Carousel> getCarouselList(){
		String sql = "select " + field + " from news where news_type=1 order by id";
		return this.getList(sql, new Object[]{}, Carousel.class);
	}
	/**
	 * 保存轮播
	 * @param carousel
	 */
	public void saveCarousel(Carousel carousel) {
		this.insert(carousel);
		
	}
	/**
	 * 获取轮播
	 * @param carouselId
	 * @return
	 */
	public Carousel getCarousel(String carouselId) {
		return this.get(carouselId, Carousel.class);
	}
	/**
	 * 修改轮播
	 * @param carousel
	 */
	public void updateCarousel(Carousel carousel) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		field.append(" title=?,content=?, pic_url=?,url=?,updated_at=?,status=?,news_type=?");
		List<Object> list = new ArrayList<Object>();
		
		list.add(carousel.getTitle());
		list.add(carousel.getContent());
		list.add(carousel.getPic_url());
		list.add(carousel.getUrl());
		list.add(carousel.getUpdated_at());
		list.add(carousel.getStatus());
		list.add(carousel.getNews_type());
		sql.append("update news set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(carousel.getId());
		this.update(sql.toString(), list.toArray());
		
	}
	/**
	 * 禁用轮播
	 * @param carouselId
	 */
	public void forbidden(String carouselId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		Long updated_at = new Date().getTime();
		field.append(" updated_at="+updated_at+",status=3 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update news set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(carouselId);
		this.update(sql.toString(), list.toArray());
		
	}
	/**
	 * 启用轮播
	 * @param carouselId
	 */
	public void release(String carouselId) {
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		Long updated_at = new Date().getTime();
		field.append(" updated_at="+updated_at+",status=0 ");
		List<Object> list = new ArrayList<Object>();
		
		sql.append("update news set ");
		sql.append(field);
		sql.append(" where id=? ");
		list.add(carouselId);
		this.update(sql.toString(), list.toArray());
		
	}
	/**
	 * 删除轮播
	 * @param carouselId
	 */
	public void removeCarousel(String carouselId) {
		String sql = "delete from news where id=?";
		Object[] args = new Object[] { carouselId };
		this.update(sql, args);
		
	}
	/**
	 * 获取轮播列表
	 * @param carousel
	 * @param start
	 * @param limit
	 * @return
	 */
	public Map<String, Object> getPage(Carousel carousel, int start, int limit) {
		StringBuffer sql = new StringBuffer();
		sql.append("select " + field + " from news where news_type=1 order by id");
		List<Object> list = new ArrayList<Object>();
		return this.getPage(sql.toString(), list.toArray(), start, limit);
	}
}