package com.yinzhi.platform.module.app.controller;

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
import com.yinzhi.platform.entity.app.AppUser;
import com.yinzhi.platform.entity.app.Carousel;
import com.yinzhi.platform.module.app.service.IAppUserService;

@Controller
@RequestMapping("/app/appuser")
public class AppUserController extends BaseController {
	@Autowired
	private IAppUserService userService;
	/**
	 * 显示首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/index", method = RequestMethod.GET)
	protected String index(Model model, HttpServletRequest request) {
		return "/app/appuser/index";
	}
	
	/**
	 * 获取用户信息列表
	 * @return
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	public List<AppUser> getUserList() {
		return this.userService.getUserList();
	}

	
	/**
	 * 禁用
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "forbidden", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forbidden(String userId) {
		
		this.userService.forbiddenUser(userId);
		
		return jsonView(true, "禁用用户成功");
	}
	
	/**
	 * 解禁
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "release", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> release(String userId) {
		
		this.userService.release(userId);
		
		return jsonView(true, "解禁用户成功");
	}
	
	
	/**
	 * 用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage")
	@ResponseBody
	public Map<String, Object> getPage( AppUser appUser, HttpServletRequest request) {
		return this.userService.getPage(appUser, getStart(request), getLimit(request));
	}
	
}
