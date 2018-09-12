package com.yinzhi.platform.module.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import com.yinzhi.platform.core.BaseController;
import com.yinzhi.platform.entity.app.Carousel;
import com.yinzhi.platform.entity.app.FlushNews;
import com.yinzhi.platform.module.app.service.ICarouselService;
import com.yinzhi.platform.utils.Jpush;

@Controller
@RequestMapping("/app/carousel")
public class CarouselController extends BaseController {
	@Autowired
	private ICarouselService carouseService;
	/**
	 * 显示首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/index", method = RequestMethod.GET)
	protected String index(Model model, HttpServletRequest request) {
		return "/app/carousel/index";
	}
	
	/**
	 * 显示添加页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/add", method = RequestMethod.GET)
	protected String add(Model model, HttpServletRequest request) {
		return "/app/carousel/add";
	}	
	
	/**
	 * 保存轮播
	 * @param file
	 * @param carousel
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveCarousel(@RequestParam(value = "file", required = true) MultipartFile file, Carousel carousel) throws IllegalStateException, IOException 
			   {
			carousel.setCreated_at(new Date().getTime());
			carousel.setNews_type(1);
			 String pic_path = "D:\\admin\\tokenhub-admin\\WebContent\\images\\";
		        String fileName = file.getOriginalFilename();
		        File targetFile = new File(pic_path, fileName);
		        if (!targetFile.exists()) {
		            targetFile.mkdirs();
		        }
		        file.transferTo(targetFile);
		        String fileUrl = fileName;
		        carousel.setPic_url(fileUrl);
		this.carouseService.saveCarousel(carousel);
		return jsonView(true, SAVE_SUCCESS);
	}
	

	/**
	 * 显示修改页面
	 * @param carouselId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/edit", method = RequestMethod.GET)
	protected String edit(String carouselId, Model model, HttpServletRequest request) {
		model.addAttribute("carousel", this.carouseService.getCarousel(carouselId));
		return "/app/carousel/edit";
	}
	
	/**
	 * 修改轮播
	 * @param carousel
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editCarousel(Carousel carousel, @RequestParam(value = "file", required = true) MultipartFile file) throws IllegalStateException, IOException {
		String pic_path = "D:\\admin\\tokenhub-admin\\WebContent\\images\\";
        String fileName = file.getOriginalFilename();
        File targetFile = new File(pic_path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
            file.transferTo(targetFile);
        }
        String fileUrl = fileName;
        if(fileUrl.equals("")) {
        	carousel.setPic_url(carousel.getPic_url());
        }else {
        	carousel.setPic_url(fileUrl);
        }
		this.carouseService.updateCarousel(carousel);
		return jsonView(true, UPDATE_SUCCESS);
	}
	
	/**
	 * 轮播列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage")
	@ResponseBody
	public Map<String, Object> getPage( Carousel carousel, HttpServletRequest request) {
		return this.carouseService.getPage(carousel, getStart(request), getLimit(request));
	}

	/**
	 * 删除轮播
	 * @param carouselId
	 * @return
	 */
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeCarousel(String carouselId) {
		
		this.carouseService.removeCarousel(carouselId);
		
		return jsonView(true, REMOVE_SUCCESS);
	}
	
	/**
	 * 禁用
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "forbidden", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forbidden(String carouselId) {
		
		this.carouseService.forbidden(carouselId);
		
		return jsonView(true, "禁用信息成功");
	}
	
	/**
	 *启用
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "release", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> release(String carouselId) {
		
		this.carouseService.release(carouselId);
		
		return jsonView(true, "启用信息成功");
	}
	
	/**
	 * 推送消息
	 * @param flushId
	 * @param color
	 * @return
	 */
	@RequestMapping(value = "pushCarousel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pushCarousel(String carouselId) {
		Carousel carousel = this.carouseService.getCarousel(carouselId);
		if(carousel.getContent() == null) {
			carousel.setContent("");
		}
		new Jpush().sendMessage(null, carousel.getContent(), null, true);
		return jsonView(true, "推送消息成功");
	}
	}
