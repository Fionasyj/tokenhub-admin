package com.yinzhi.platform.module.app.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.entity.app.Carousel;
import com.yinzhi.platform.module.app.dao.CarouselDao;
import com.yinzhi.platform.module.app.service.ICarouselService;

@Service
public class CarouselService implements ICarouselService{
	
	@Autowired
	private CarouselDao carouselDao;



	public List<Carousel> getCarouselList() {
		return this.carouselDao.getCarouselList();

	}



	@Override
	public void saveCarousel(Carousel carousel) {
		this.carouselDao.saveCarousel(carousel);
		
	}


	@Override
	public Carousel getCarousel(String carouselId) {
		return this.carouselDao.getCarousel(carouselId);
	}


	@Override
	public void updateCarousel(Carousel carousel) {
		this.carouselDao.updateCarousel(carousel);
		
	}


	@Override
	public void forbidden(String carouselId) {
		this.carouselDao.forbidden(carouselId);
		
	}


	@Override
	public void release(String carouselId) {
		this.carouselDao.release(carouselId);
		
	}



	@Override
	public void removeCarousel(String carouselId) {
		this.carouselDao.removeCarousel(carouselId);
		
	}



	@Override
	public Map<String, Object> getPage(Carousel carousel, int start, int limit) {
		return this.carouselDao.getPage(carousel, start, limit);
	}
}
