package com.yinzhi.platform.module.app.service;

import java.util.List;
import java.util.Map;

import com.yinzhi.platform.entity.app.Carousel;
public interface ICarouselService {
	/**
	 * 返回轮播列表
	 * @param userId
	 * @return
	 */
	List<Carousel> getCarouselList();
	
	/**
	 * 保存轮播
	 * @param carousel
	 */
	void saveCarousel(Carousel carousel);
	/**
	 * 获取轮播实体
	 * @param carouselId
	 * @return
	 */
	Carousel getCarousel(String carouselId);
	/**
	 * 修改轮播
	 * @param carousel
	 */
	void updateCarousel(Carousel carousel);
	/**
	 * 禁用轮播
	 * @param carouselId
	 */
	void forbidden(String carouselId);
	/**
	 * 启用轮播
	 * @param carouselId
	 */
	void release(String carouselId);
	/**
	 * 删除轮播
	 * @param carouselId
	 */
	void removeCarousel(String carouselId);

	/**
	 * 获取分页信息
	 * @param carousel
	 * @param start
	 * @param limit
	 * @return
	 */
	Map<String, Object> getPage(Carousel carousel, int start, int limit);
}
