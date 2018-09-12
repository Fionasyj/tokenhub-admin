package com.yinzhi.platform.module.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yinzhi.platform.core.BaseController;
import com.yinzhi.platform.entity.app.FlushNews;
import com.yinzhi.platform.module.app.service.IFlushNewsService;
import com.yinzhi.platform.utils.Jpush;


@Controller
@RequestMapping("/app/flushnews")
public class FlushNewsController extends BaseController {
	@Autowired
	private IFlushNewsService flushNewsService;
	private Jpush jpush;
	/**
	 * 显示首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/index", method = RequestMethod.GET)
	protected String index(Model model, HttpServletRequest request) {
		return "/app/flushnews/index";
	}
	
	/**
	 * 显示添加页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/add", method = RequestMethod.GET)
	protected String add(Model model, HttpServletRequest request) {
		return "/app/flushnews/add";
	}	
	
	/**
	 * 保存快讯
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveFlushNews(FlushNews flushNews) {
		flushNews.setCreated_at(System.currentTimeMillis());
		flushNews.setPush_times(0);
		flushNews.setSource_media(3);
		this.flushNewsService.saveFlushNews(flushNews);
		return jsonView(true, SAVE_SUCCESS);
	}
	
	/**
	 * 显示修改页面
	 * @param flushnewsId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/edit", method = RequestMethod.GET)
	protected String edit(String flushnewsId, Model model, HttpServletRequest request) {
		model.addAttribute("flushnews", this.flushNewsService.getFlushNews(flushnewsId));
		return "/app/flushnews/edit";
	}
	
	/**
	 * 修改快讯
	 * @param flushNews
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editFlushNews(FlushNews flushNews) {
		this.flushNewsService.updateFlushNews(flushNews);
		return jsonView(true, UPDATE_SUCCESS);
	}
	
	/**
	 * 快讯列表信息
	 * @return
	 */
	@RequestMapping("/getFlushNewsList")
	@ResponseBody
	public List<FlushNews> getFlushNewsList() {
		return this.flushNewsService.getFlushNewsList();
	}
	
	
	/**
	 * 标记颜色
	 * @param flushId
	 * @param color
	 * @return
	 */
	@RequestMapping(value = "markColor", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> markColor(String flushId, String color) {
		if(color.equals("255,0,0")) {
			color = "#FF0000";
		}else if(color.equals("0,0,255")) {
			color = "#0000FF";
		}else if(color.equals("255,255,0")) {
			color = "#FFFF00";
		}
		this.flushNewsService.markColor(flushId, color);
		return jsonView(true, "标记颜色成功");
	}
	
	/**
	 * 快讯列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage")
	@ResponseBody
	public Map<String, Object> getPage( FlushNews flushNews,HttpServletRequest request) {
		return this.flushNewsService.getPage(flushNews, getStart(request), getLimit(request));
	}
	
	/**
	 * 推送消息
	 * @param flushId
	 * @param color
	 * @return
	 */
	@RequestMapping(value = "pushNews", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pushNews(String flushId, String content_color) {
		FlushNews flushnews = this.flushNewsService.getFlushNews(flushId);
		if(flushnews.getPush_times() != null && !flushnews.getPush_times().equals("")) {
			flushnews.setPush_times(flushnews.getPush_times()+1);
		}else {
			flushnews.setPush_times(1);
		}
		if(content_color.equals("255,0,0")) {
			content_color = "#FF0000";
		}else if(content_color.equals("0,0,255")) {
			content_color = "#0000FF";
		}else if(content_color.equals("255,255,0")) {
			content_color = "#FFFF00";
		}
		this.flushNewsService.updateFlushNews(flushnews);
		this.flushNewsService.markColor(flushId, content_color);
		this.jpush.sendMessage(null, flushnews.getContent(), null, true);
		return jsonView(true, "推送消息成功");
	}
	
	/**
	 * 禁用
	 * @param flushId
	 * @return
	 */
	@RequestMapping(value = "forbidden", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forbidden(String flushId) {
		
		this.flushNewsService.forbidden(flushId);
		
		return jsonView(true, "禁用信息成功");
	}
	
	/**
	 * 启用
	 * @param flushId
	 * @return
	 */
	@RequestMapping(value = "release", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> release(String flushId) {
		
		this.flushNewsService.release(flushId);
		
		return jsonView(true, "启用信息成功");
	}
	/**
	 * 删除用户
	 * @param flushnewsId
	 * @return
	 */
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeUser(String flushnewsId) {

		this.flushNewsService.removeFlushNews(flushnewsId);
		
		return jsonView(true, REMOVE_SUCCESS);
	}
}
